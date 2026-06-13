import java.util.Arrays;

class Solution {
    
    public int solution(int[] A, int[] B) {
        
        int answer = 0;

        // 1. 두 배열을 오름차순으로 정렬합니다.
        Arrays.sort(A);
        Arrays.sort(B);

        // 2. A의 최솟값(앞쪽)과 B의 최댓값(뒤쪽)을 곱하여 누적합니다.
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[B.length - 1 - i];
        }

        return answer;
    }
}