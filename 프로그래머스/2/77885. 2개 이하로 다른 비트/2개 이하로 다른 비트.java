class Solution {
    
    public long[] solution(long[] numbers) {
        
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];

            // 짝수
            if (x % 2 == 0) {
                answer[i] = x + 1;
            }
            // 홀수
            else {
                long next = x + 1;
                answer[i] = next | ((x ^ next) >> 2);
            }
        }

        return answer;
    }
}