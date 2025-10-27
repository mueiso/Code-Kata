import java.util.*;

class Solution {
    
    public int[] solution(int[] arr) {
        
        // 1. 배열에 원소가 하나뿐이면 제거 후 빈 배열이 되므로 [-1] 반환
        if (arr.length == 1)
            return new int[]{-1};

        // 2. 배열에서 최솟값 찾기
        int min = Arrays.stream(arr)
                        .min()           // 최소값 OptionalInt로 반환
                        .getAsInt();     // 실제 int 값 꺼내기

        // 3. 최솟값을 제외한 나머지 요소만 필터링해서 배열로 반환
        return Arrays.stream(arr)
                     .filter(n -> n != min) // 최솟값이 아닌 값만 통과
                     .toArray();             // int[]로 변환
    }
}