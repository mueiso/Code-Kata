import java.util.*;

class Solution {
    public int[] solution(int[] numlist, int n) {
        // Integer 배열로 변환 (정렬을 위해)
        Integer[] nums = Arrays.stream(numlist).boxed().toArray(Integer[]::new);
        
        // 사용자 정의 정렬: 거리 기준 정렬, 거리가 같으면 큰 수 우선
        Arrays.sort(nums, (a, b) -> {
            int diffA = Math.abs(a - n);
            int diffB = Math.abs(b - n);
            if (diffA == diffB) {
                return b - a; // 더 큰 수 우선
            } else {
                return diffA - diffB; // 거리 오름차순
            }
        });

        // 다시 int[]로 변환
        return Arrays.stream(nums).mapToInt(Integer::intValue).toArray();
    }
}
