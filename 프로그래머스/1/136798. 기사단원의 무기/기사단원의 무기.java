class Solution {
    
    public int solution(int number, int limit, int power) {
        
        int answer = 0;

        // 1번 기사부터 number번 기사까지
        for (int i = 1; i <= number; i++) {
            int divisorCount = countDivisors(i);

            // 제한 수치 초과 시 power로 대체
            if (divisorCount > limit) {
                answer += power;
            } else {
                answer += divisorCount;
            }
        }

        return answer;
    }

    // 어떤 수 n의 약수 개수를 구하는 메서드
    private int countDivisors(int n) {
        int count = 0;

        // 1부터 √n 까지만 확인
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++;          // i는 약수
                if (i * i != n) { // n / i도 i와 다른 약수라면 하나 더
                    count++;
                }
            }
        }

        return count;
    }
}