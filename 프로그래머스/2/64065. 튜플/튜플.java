import java.util.*;

class Solution {
    
    public int[] solution(String s) {
        
        // 1. 문자열의 앞뒤 {{ , }} 를 제거하고 "},{" 를 기준으로 자릅니다.
        // 정규식에서 { 와 } 는 예약어이므로 \\를 붙여줍니다.
        String[] groups = s.substring(2, s.length() - 2).split("\\},\\{");

        // 2. 각 집합을 원소 개수가 적은 순으로 정렬합니다.
        Arrays.sort(groups, (a, b) -> a.length() - b.length());

        // 3. 결과값을 담을 리스트와 중복 체크를 위한 Set 준비
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (String group : groups) {
            // 각 집합 내의 숫자들을 분리
            String[] elements = group.split(",");
            for (String element : elements) {
                int num = Integer.parseInt(element);
                
                // 아직 결과 리스트에 담기지 않은 숫자라면 추가
                if (!visited.contains(num)) {
                    visited.add(num);
                    result.add(num);
                }
            }
        }

        // 4. 리스트를 int 배열로 변환하여 반환
        return result.stream().mapToInt(i -> i).toArray();
    }
}