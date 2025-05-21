public class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int sum = 0;

        for (int i = 0; i < absolutes.length; i++) {
            // signs[i]가 true면, absolutes[i]는 양수,
            // signs[i]가 false면, absolutes[i]는 음수
            sum += signs[i] ? absolutes[i] : -absolutes[i];
        }

        return sum;
    }
}