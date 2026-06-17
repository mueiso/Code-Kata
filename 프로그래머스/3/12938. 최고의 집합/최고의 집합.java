import java.util.Arrays;

class Solution {
    
    public int[] solution(int n, int s) {
        
        // 1. n개의 자연수로 s를 만드는 것이 불가능한 경우 (최소 s는 n이어야 함)
        if (s < n) {
            return new int[]{-1};
        }

        int[] answer = new int[n];
        int base = s / n;       // 모든 원소의 기본값 (몫)
        int remainder = s % n;  // 공평하게 나눠주고 남은 값 (나머지)

        // 2. 일단 모든 원소를 가장 공평한 기본값으로 채웁니다.
        for (int i = 0; i < n; i++) {
            answer[i] = base;
        }

        // 3. 오름차순을 유지하기 위해 '맨 뒤에서부터' 남은 값을 1씩 분배합니다.
        for (int i = 0; i < remainder; i++) {
            answer[n - 1 - i]++;
        }

        return answer;
    }
}