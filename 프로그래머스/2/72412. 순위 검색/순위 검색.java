import java.util.*;

class Solution {
    
    static Map<String, List<Integer>> allInfo;

    public int[] solution(String[] info, String[] query) {
        
        int[] answer = new int[query.length];
        allInfo = new HashMap<>();

        // 1. 각 지원자 정보를 바탕으로 16가지 조합 생성
        for (String s : info) {
            String[] split = s.split(" ");
            makeSentence(split, "", 0);
        }

        // 2. 점수 리스트 정렬 (이진 탐색 준비)
        for (String key : allInfo.keySet()) {
            Collections.sort(allInfo.get(key));
        }

        // 3. 쿼리 수행
        for (int i = 0; i < query.length; i++) {
            String q = query[i].replace(" and ", "");
            String[] split = q.split(" ");
            
            String key = split[0];
            int score = Integer.parseInt(split[1]);

            answer[i] = allInfo.containsKey(key) ? binarySearch(key, score) : 0;
        }

        return answer;
    }

    /* 모든 조건 조합을 만드는 재귀 함수 */
    private void makeSentence(String[] split, String str, int cnt) {
        
        if (cnt == 4) {
            int score = Integer.parseInt(split[4]);
            allInfo.computeIfAbsent(str, k -> new ArrayList<>()).add(score);
            
            return;
        }
        makeSentence(split, str + "-", cnt + 1);
        makeSentence(split, str + split[cnt], cnt + 1);
    }

    /* 이진 탐색 (Lower Bound) */
    private int binarySearch(String key, int score) {
        
        List<Integer> list = allInfo.get(key);
        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return list.size() - start;
    }
}