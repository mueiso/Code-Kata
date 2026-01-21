class Solution {

    public int solution(int n, int k) {
        
        int answer = 0;

        // 1. n을 k진수 문자열로 변환
        String converted = Integer.toString(n, k);

        // 2. 0을 기준으로 분리
        String[] parts = converted.split("0");

        // 3. 각 숫자 조각에 대해 소수 판별
        for (String part : parts) {
            if (part.isEmpty()) continue; // 빈 문자열 무시

            long num = Long.parseLong(part); // 10진수로 변환

            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    // 소수 판별 함수
    private boolean isPrime(long num) {
        if (num < 2) return false;

        // 2부터 √num 까지만 확인
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}