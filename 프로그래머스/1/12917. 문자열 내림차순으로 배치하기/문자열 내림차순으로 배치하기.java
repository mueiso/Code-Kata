import java.util.stream.Collectors;

class Solution {
    
    public String solution(String s) {
        
        return s.chars()                            // IntStream 생성 (문자 코드 기반)
                .mapToObj(c -> (char) c)            // IntStream → Stream<Character>
                .sorted((a, b) -> b - a)            // 내림차순 정렬
                .map(String::valueOf)               // Character → String
                .collect(Collectors.joining());     // 문자열로 합침
    }
}