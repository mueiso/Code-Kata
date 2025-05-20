import java.util.*;
import java.util.stream.*;

class Solution {
    public long solution (long n) {
        // n을 문자열로 변환하고 각 자릿수를 List로 변환
        String str = String.valueOf(n);
        
        // 자릿수를 내림차순으로 정렬하고, 다시 숫자로 합치기
        return Long.parseLong(
            str.chars()                               // 문자열을 IntStream으로 변환
                .mapToObj(c -> (char) c)              // char로 변환
                .sorted(Comparator.reverseOrder())    // 내림차순으로 정렬
                .map(String::valueOf)                 // String으로 변환
                .collect(Collectors.joining())        // 하나의 문자열로 합치기
        );
    }
}
