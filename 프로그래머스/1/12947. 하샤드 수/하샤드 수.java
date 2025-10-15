class Solution {
    
    public boolean solution(int x) {
        
        // 1. x를 문자열로 바꾸고 각 문자(숫자)를 int로 변환하여 합 구하기
        int sum = String.valueOf(x)
                        .chars()              // 문자 스트림 (예: 18 → ['1','8'])
                        .map(ch -> ch - '0')  // 문자 '0'을 빼서 실제 숫자로 변환
                        .sum();               // 각 자리의 합 계산 (1 + 8 = 9)

        // 2. 하샤드 수 판별: x가 자릿수 합으로 나누어떨어지면 true
        return x % sum == 0;
    }
}