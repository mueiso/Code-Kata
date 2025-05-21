class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            answer += a[i] * b[i];  // 각 배열의 같은 인덱스를 곱한 값을 answer에 누적
        }
        return answer;
    }
}