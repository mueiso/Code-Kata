class Solution {
    
    public int[] solution(int[] arr, int k) {
        
        int[] answer = new int[arr.length];

        if (k % 2 == 1) { 
            // k가 홀수 → 모든 원소에 k를 곱함
            for (int i = 0; i < arr.length; i++) {
                answer[i] = arr[i] * k;
            }

        } else {
            // k가 짝수 → 모든 원소에 k를 더함
            for (int i = 0; i < arr.length; i++) {
                answer[i] = arr[i] + k;
            }
        }

        return answer;
    }
}