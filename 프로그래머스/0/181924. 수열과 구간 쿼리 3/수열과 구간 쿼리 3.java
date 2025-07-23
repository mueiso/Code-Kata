class Solution {
    
    public int[] solution(int[] arr, int[][] queries) {
        
        for (int[] query : queries) {
            int i = query[0];
            int j = query[1];

            // i와 j 위치의 값 교환
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        
        return arr;
    }
}
