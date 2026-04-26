import java.util.*;
import java.util.function.Function;

class Solution {
    
    public int solution(int n, Function<Integer, String> submit) {
        
        // 모든 유효한 숫자 리스트 (전체 집합)
        List<Integer> allPossible = new ArrayList<>();
        for (int i = 1234; i <= 9876; i++) {
            if (isValid(i)) allPossible.add(i);
        }

        // 현재 필터링된 정답 후보 리스트
        List<Integer> candidates = new ArrayList<>(allPossible);

        // 첫 번째 수는 고정 (1234가 정보 획득 효율이 좋음)
        int guess = 1234;

        while (true) {
            String result = submit.apply(guess);
            if (result.equals("4S 0B")) return guess;

            int s = result.charAt(0) - '0';
            int b = result.charAt(3) - '0';

            // 1. 결과에 따라 후보군 필터링
            List<Integer> nextCandidates = new ArrayList<>();
            for (int cand : candidates) {
                if (getSB(guess, cand) == (s * 10 + b)) {
                    nextCandidates.add(cand);
                }
            }
            candidates = nextCandidates;

            if (candidates.size() == 1) {
                guess = candidates.get(0);
                continue;
            }

            // 2. 미니맥스 알고리즘으로 다음 guess 선정
            int bestGuess = -1;
            int minOfMaxGroup = Integer.MAX_VALUE;

            // 시간 효율을 위해 후보군이 많을 때는 candidates에서, 적을 때는 allPossible에서 탐색
            List<Integer> searchSpace = (candidates.size() > 100) ? candidates : allPossible;

            for (int g : searchSpace) {
                int[] scoreCounts = new int[50]; // S*10 + B 인덱스 활용
                for (int c : candidates) {
                    scoreCounts[getSB(g, c)]++;
                }

                int maxInGroup = 0;
                for (int count : scoreCounts) {
                    maxInGroup = Math.max(maxInGroup, count);
                }

                // 최악의 경우 남는 후보의 수를 최소화하는 수 선택
                if (maxInGroup < minOfMaxGroup) {
                    minOfMaxGroup = maxInGroup;
                    bestGuess = g;
                } else if (maxInGroup == minOfMaxGroup) {
                    // 동일하다면 실제 정답 후보인 쪽을 우선 (빨리 맞히기 위해)
                    if (!candidates.contains(bestGuess) && candidates.contains(g)) {
                        bestGuess = g;
                    }
                }
            }
            guess = bestGuess;
        }
    }

    private boolean isValid(int num) {
        
        int[] d = {num / 1000, (num / 100) % 10, (num / 10) % 10, num % 10};
        
        if (d[0] == 0 || d[1] == 0 || d[2] == 0 || d[3] == 0) return false;
        
        return d[0] != d[1] && d[0] != d[2] && d[0] != d[3] && d[1] != d[2] && d[1] != d[3] && d[2] != d[3];
    }

    private int getSB(int g, int c) {
        
        int s = 0, b = 0;
        int g1 = g / 1000, g2 = (g / 100) % 10, g3 = (g / 10) % 10, g4 = g % 10;
        int c1 = c / 1000, c2 = (c / 100) % 10, c3 = (c / 10) % 10, c4 = c % 10;

        if (g1 == c1) s++; else if (g1 == c2 || g1 == c3 || g1 == c4) b++;
        if (g2 == c2) s++; else if (g2 == c1 || g2 == c3 || g2 == c4) b++;
        if (g3 == c3) s++; else if (g3 == c1 || g3 == c2 || g3 == c4) b++;
        if (g4 == c4) s++; else if (g4 == c1 || g4 == c2 || g4 == c3) b++;
        
        return s * 10 + b;
    }
}