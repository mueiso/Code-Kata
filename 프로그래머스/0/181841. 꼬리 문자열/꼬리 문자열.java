import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    
    public String solution(String[] str_list, String ex) {
        
        // ex를 포함하지 않는 문자열만 필터링 후 이어붙이기
        return Arrays.stream(str_list)
                     .filter(s -> !s.contains(ex))   // ex가 포함되지 않은 문자열만
                     .collect(Collectors.joining()); // 하나의 문자열로 연결
    }
}