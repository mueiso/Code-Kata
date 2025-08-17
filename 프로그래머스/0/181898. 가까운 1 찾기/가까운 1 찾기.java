class Solution {
    public int solution(int[] arr, int idx) {
        // idx부터 탐색 시작해야 함 (조건이 'idx 이상'이 아니라 'idx보다 크거나 같은')
        for (int i = idx; i < arr.length; i++) {
            if (arr[i] == 1) {
                return i;
            }
        }
        return -1;
    }
}
