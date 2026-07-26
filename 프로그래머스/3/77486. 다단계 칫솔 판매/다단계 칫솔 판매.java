import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        int n = enroll.length;
        int[] answer = new int[n];

        // 1. 이름 -> enroll 인덱스 매핑
        Map<String, Integer> nameToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nameToIndex.put(enroll[i], i);
        }

        // 2. 부모(추천인) 인덱스 배열 생성
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1; // 추천인이 없으면 -1
            } else {
                parent[i] = nameToIndex.get(referral[i]);
            }
        }

        // 3. 각 판매 기록 처리
        for (int i = 0; i < seller.length; i++) {
            int currIndex = nameToIndex.get(seller[i]);
            int currMoney = amount[i] * 100; // 칫솔 개당 100원

            // 상위 추천인으로 거슬러 올라가며 이익 분배
            while (currIndex != -1 && currMoney > 0) {
                int tax = currMoney / 10;       // 추천인에게 분배할 10% (원 단위 절사)
                int keep = currMoney - tax;     // 자신이 가질 90%

                answer[currIndex] += keep;

                // 10% 분배금이 0원이면 더 이상 상위로 올라갈 필요 없음
                if (tax == 0) {
                    break;
                }

                // 다음 상위 추천인으로 이동
                currIndex = parent[currIndex];
                currMoney = tax;
            }
        }

        return answer;
    }
}