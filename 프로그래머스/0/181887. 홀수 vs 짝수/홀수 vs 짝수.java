class Solution {
    
    public int solution(int[] num_list) {
        
        int oddSum = 0;   // 홀수 번째 원소들의 합
        int evenSum = 0;  // 짝수 번째 원소들의 합

        // 첫 번째 원소를 1번으로 간주 → index 0 은 홀수 번째
        for (int i = 0; i < num_list.length; i++) {
            
            if ((i + 1) % 2 == 1) { // 홀수 번째
                oddSum += num_list[i];
            } else { // 짝수 번째
                evenSum += num_list[i];
            }
        }

        // 홀수 합과 짝수 합 중 큰 값, 같으면 그대로 반환
        return Math.max(oddSum, evenSum);
    }
}
