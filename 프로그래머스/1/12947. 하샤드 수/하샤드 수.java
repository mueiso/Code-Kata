class Solution {
    
    public boolean solution(int x) {
        
        int sum = 0;
        int originalX = x; // x 값을 보존하기 위해 원본 값을 저장

        // x의 각 자릿수 합 구하기
        while (x > 0) {
            sum += (x % 10); // 마지막 자릿수 더하기
            x /= 10; // 마지막 자릿수 제거
        }

        // x가 자릿수 합으로 나누어 떨어지는지 확인
        return originalX % sum == 0;
    }
}