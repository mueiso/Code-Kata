class Solution {
    
    public int solution(int[] numbers) {
        
        // 0~9의 합 = 45
        int sum = 45;

        // numbers 배열의 숫자들을 하나씩 빼서
        // 없는 숫자의 합만 남기기
        for (int i : numbers) {
            sum -= i;
        }

        // 최종적으로 남은 합 = 없는 숫자들의 합
        return sum;
    }
}
