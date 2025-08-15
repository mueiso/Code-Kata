class Solution {
    
    public int[] solution(int n, int k) {
        
        // k의 배수 개수 계산 (n/k)
        int size = n / k;
        int[] answer = new int[size];
        
        // k, 2k, 3k, ... 채우기
        for (int i = 0; i < size; i++) {
            answer[i] = k * (i + 1);
        }
        
        return answer;
    }
}
