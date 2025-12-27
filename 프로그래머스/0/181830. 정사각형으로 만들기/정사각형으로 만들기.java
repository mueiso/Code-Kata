class Solution {
    
    public int[][] solution(int[][] arr) {
        
        int max = Math.max(arr.length, arr[0].length);
        int[][] result = new int[max][max];

        for (int i = 0; i < arr.length; i++) {
            // 각 행의 실제 길이만큼 복사
            System.arraycopy(arr[i], 0, result[i], 0, arr[i].length);
        }

        return result;
    }
}