import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        
        if (arr.length == 1) return new int[]{-1};

        int min = Arrays.stream(arr).min().getAsInt();  // 배열에서 최솟값 찾기
        
        return Arrays.stream(arr).filter(n -> n != min).toArray(); // 최솟값 제외한 배열 리턴
    }
}