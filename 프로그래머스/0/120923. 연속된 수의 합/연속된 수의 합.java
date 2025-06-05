class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        
        // 연속된 num개의 수의 합 = total
        // 연속된 수를 a, a+1, a+2, ..., a+(num-1)라고 하면
        // 합 = num * (2a + (num - 1)) / 2 = total
        // 이를 통해 a(시작값)를 구할 수 있음
        
        int start = (2 * total / num - (num - 1)) / 2;
        
        for (int i = 0; i < num; i++) {
            answer[i] = start + i;
        }
        
        return answer;
    }
}
