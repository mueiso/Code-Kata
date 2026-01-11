class Solution {
    
    public int[] solution(int n, long left, long right) {

        int len = (int)(right - left + 1); // 구해야 할 구간 길이
        int[] answer = new int[len];

        // left부터 right까지 직접 값 계산
        for (long idx = left; idx <= right; idx++) {
            int row = (int)(idx / n);      // 0-index 행
            int col = (int)(idx % n);      // 0-index 열
            int value = Math.max(row, col) + 1; // 규칙: max(row, col) + 1

            answer[(int)(idx - left)] = value;
        }

        return answer;
    }
}