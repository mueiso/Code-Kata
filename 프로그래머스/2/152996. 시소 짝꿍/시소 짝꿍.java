class Solution {
    
    public long solution(int[] weights) {
        
        long answer = 0;
        
        long[] arr = new long[1001];

        // 1. 몸무게 개수 카운팅
        for (int w : weights) {
            arr[w]++;
        }

        // 2. 모든 몸무게에 대해 계산
        for (int i = 100; i <= 1000; i++) {
            if (arr[i] == 0) continue;

            // 1:1 (같은 무게)
            answer += (arr[i] * (arr[i] - 1)) / 2;

            // 4:3 (i : target = 3 : 4)
            if (i % 3 == 0) {
                int target = (4 * i) / 3;
                if (target <= 1000) {
                    answer += arr[i] * arr[target];
                }
            }

            // 3:2 (i : target = 2 : 3)
            if (i % 2 == 0) {
                int target = (3 * i) / 2;
                if (target <= 1000) {
                    answer += arr[i] * arr[target];
                }
            }

            // 2:1 (i : target = 1 : 2)
            if (2 * i <= 1000) {
                answer += arr[i] * arr[2 * i];
            }
        }

        return answer;
    }
}