class Solution {
    
    public int solution(int[] numbers, int n) {
        
        int sum = 0;

        for (int num : numbers) {
            sum += num;
            if (sum > n) {
                return sum;
            }
        }

        return sum;  // 끝까지 합해도 n을 넘지 못한 경우
    }
}
