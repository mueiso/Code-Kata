import java.util.Arrays;

class Solution {
    
    public int[] solution(int[] arr, int[] query) {
        
        // query 배열을 순회하면서 조건에 맞게 arr을 잘라줌
        for (int i = 0; i < query.length; i++) {
            int q = query[i]; // 현재 query 값

            if (i % 2 == 0) {
                // 짝수 인덱스 → query[i] 뒤쪽을 잘라버림 (자기 자신 포함 X)
                arr = Arrays.copyOfRange(arr, 0, q + 1);
            } else {
                // 홀수 인덱스 → query[i] 앞쪽을 잘라버림 (자기 자신 포함 X)
                arr = Arrays.copyOfRange(arr, q, arr.length);
            }
        }
        
        return arr;
    }
}
