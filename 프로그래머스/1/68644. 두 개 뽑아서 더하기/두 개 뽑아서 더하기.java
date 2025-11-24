import java.util.*;

class Solution {
    
    public int[] solution(int[] numbers) {
        
        // 중복 제거를 위해 HashSet 사용
        Set<Integer> set = new HashSet<>();

        // 두 개의 서로 다른 인덱스를 선택해 더한 값을 set에 저장
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]); // 더한 값을 추가 (중복 자동 제거)
            }
        }

        // Set → Stream → 오름차순 정렬 → int 배열 변환
        return set.stream()
                   .sorted()                     // 오름차순 정렬
                   .mapToInt(Integer::intValue)  // Integer → int 변환
                   .toArray();                   // 최종 int[] 반환
    }
}