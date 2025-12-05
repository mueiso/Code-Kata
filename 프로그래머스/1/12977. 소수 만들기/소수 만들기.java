class Solution {
    
    public int solution(int[] nums) {
        
        int answer = 0;
        int n = nums.length;

        // 서로 다른 3개 인덱스 i, j, k (i < j < k) 선택
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {

                    int sum = nums[i] + nums[j] + nums[k];

                    // 합이 소수라면 경우의 수 증가
                    if (isPrime(sum)) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    // 소수 판별 메서드
    private boolean isPrime(int num) {
        if (num < 2) {
            return false; // 0, 1은 소수 아님
        }

        // 2부터 √num 까지만 검사
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false; // 나누어 떨어지면 소수 아님
            }
        }
        return true; // 끝까지 안 나누어지면 소수
    }
}