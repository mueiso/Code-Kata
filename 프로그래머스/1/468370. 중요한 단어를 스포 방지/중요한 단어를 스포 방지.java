import java.util.*;

class Solution {
    
    private static class Word {
        
        String text;
        int start;
        int end;

        Word(String text, int start, int end) {
            this.text = text;
            this.start = start;
            this.end = end;
        }
    }

    public int solution(String message, int[][] spoiler_ranges) {
        
        int n = message.length();
        int numRanges = spoiler_ranges.length;

        // 1. 메시지의 각 인덱스가 속한 스포 방지 구간 번호 매핑 (-1: 비스포일러)
        int[] rangeOfIndex = new int[n];
        Arrays.fill(rangeOfIndex, -1);
        for (int r = 0; r < numRanges; r++) {
            for (int k = spoiler_ranges[r][0]; k <= spoiler_ranges[r][1]; k++) {
                rangeOfIndex[k] = r;
            }
        }

        // 2. 단어 추출 (시작/끝 인덱스 기록)
        List<Word> words = new ArrayList<>();
        int idx = 0;
        while (idx < n) {
            if (message.charAt(idx) == ' ') {
                idx++;
                continue;
            }
            int start = idx;
            while (idx < n && message.charAt(idx) != ' ') {
                idx++;
            }
            int end = idx - 1;
            words.add(new Word(message.substring(start, idx), start, end));
        }

        // 3. 단어 분류 및 완전 공개 구간 매핑
        Set<String> nonSpoilerWords = new HashSet<>();
        List<Word>[] wordsRevealedAt = new ArrayList[numRanges];
        for (int i = 0; i < numRanges; i++) {
            wordsRevealedAt[i] = new ArrayList<>();
        }

        for (Word w : words) {
            int maxRange = -1;
            boolean isSpoiler = false;

            for (int k = w.start; k <= w.end; k++) {
                if (rangeOfIndex[k] != -1) {
                    isSpoiler = true;
                    maxRange = Math.max(maxRange, rangeOfIndex[k]);
                }
            }

            if (!isSpoiler) {
                nonSpoilerWords.add(w.text);
            } else {
                wordsRevealedAt[maxRange].add(w);
            }
        }

        // 4. 구간별 클릭 시뮬레이션 진행
        Set<String> revealedSpoilerWords = new HashSet<>();
        int answer = 0;

        for (int r = 0; r < numRanges; r++) {
            for (Word w : wordsRevealedAt[r]) {
                boolean isImportant = true;

                // 조건 1: 비스포일러 구간에 등장한 적이 없어야 함
                if (nonSpoilerWords.contains(w.text)) {
                    isImportant = false;
                }
                // 조건 2: 이전에 공개된 스포일러 단어와 중복되지 않아야 함
                if (revealedSpoilerWords.contains(w.text)) {
                    isImportant = false;
                }

                if (isImportant) {
                    answer++;
                }

                // 공개 처리
                revealedSpoilerWords.add(w.text);
            }
        }

        return answer;
    }
}