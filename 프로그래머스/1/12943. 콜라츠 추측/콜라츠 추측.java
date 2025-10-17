class Solution {
    
    public int solution(int num) {
        
        // long 타입으로 변환 (계산 중 오버플로우 방지)
        long n = num;      

        int count = 0;  // 반복 횟수 저장 변수

        // n이 1이 될 때까지 반복 (최대 500번까지만)
        while (n != 1 && count < 500) {

            // 짝수인 경우 → 2로 나누기
            // 홀수인 경우 → 3을 곱하고 1을 더하기
            n = (n % 2 == 0) ? (n / 2) : (n * 3 + 1);

            count++; // 반복 횟수 증가
        }

        // 500번 이내에 1이 되면 count 반환, 아니면 -1 반환
        return (n == 1) ? count : -1;
    }
}