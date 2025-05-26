import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> set = new HashSet<>();

        // 서로 다른 인덱스의 두 수를 더한 값을 set에 저장 (중복 제거)
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }

        // Stream API를 사용해 set을 정렬된 배열로 변환
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}
