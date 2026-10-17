import java.util.*;

class Solution {
    
    public int[] solution(String[] words, String[] queries) {
        
        // 단어의 길이는 최대 10,000까지 가능
        ArrayList<String>[] wordList = new ArrayList[10001];
        ArrayList<String>[] revWordList = new ArrayList[10001];
        
        for (int i = 0; i <= 10000; i++) {
            wordList[i] = new ArrayList<>();
            revWordList[i] = new ArrayList<>();
        }

        // 1. 단어들을 길이별로 정방향 및 역방향 리스트에 저장
        for (String word : words) {
            int len = word.length();
            wordList[len].add(word);
            revWordList[len].add(new StringBuilder(word).reverse().toString());
        }

        // 2. 이분 탐색을 위해 각 길이별 리스트 정렬
        for (int i = 0; i <= 10000; i++) {
            Collections.sort(wordList[i]);
            Collections.sort(revWordList[i]);
        }

        int[] answer = new int[queries.length];

        // 3. 쿼리 순회하며 매칭되는 단어 개수 탐색
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            int len = q.length();

            if (q.charAt(0) == '?') {
                if (q.charAt(q.length() - 1) == '?') {
                    // 모든 문자가 '?'인 경우 (예: "?????")
                    answer[i] = wordList[len].size();
                } else {
                    // 접두사가 '?'인 경우 (예: "??odo" -> 역방향 리스트 활용)
                    String revQ = new StringBuilder(q).reverse().toString();
                    String start = revQ.replace('?', 'a');
                    String end = revQ.replace('?', '{'); // 'z' 다음 ASCII 문자
                    answer[i] = upperBound(revWordList[len], end) - lowerBound(revWordList[len], start);
                }
            } else {
                // 접미사가 '?'인 경우 (예: "fro??")
                String start = q.replace('?', 'a');
                String end = q.replace('?', '{');
                answer[i] = upperBound(wordList[len], end) - lowerBound(wordList[len], start);
            }
        }

        return answer;
    }

    /* Lower Bound 구현 */
    private int lowerBound(ArrayList<String> list, String target) {
        
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid).compareTo(target) >= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /* Upper Bound 구현 */
    private int upperBound(ArrayList<String> list, String target) {
        
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid).compareTo(target) > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}