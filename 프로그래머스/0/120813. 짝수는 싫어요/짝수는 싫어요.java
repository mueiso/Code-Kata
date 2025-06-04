class Solution {
    public int[] solution(int n) {
        int size = (n + 1) / 2; // 홀수의 개수
        int[] answer = new int[size];
        
        int index = 0;
        for (int i = 1; i <= n; i += 2) {
            answer[index++] = i;
        }
        
        return answer;
    }
}
