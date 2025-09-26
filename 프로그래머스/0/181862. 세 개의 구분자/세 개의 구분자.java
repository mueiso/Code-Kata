import java.util.Arrays;

class Solution {
    
    public String[] solution(String myStr) {
        
        /*
         1. 문자열을 "a", "b", "c"를 구분자로 split
              정규식 [abc]+ → a, b, c 가 하나 이상 연속되면 구분자로 인식
              
         2. stream으로 변환 후 빈 문자열("") 제거
         */
        String[] arr = Arrays.stream(myStr.split("[abc]+"))
                             .filter(str -> !str.isEmpty())
                             .toArray(String[]::new);

        // 3. 결과 배열이 비어 있으면 ["EMPTY"], 아니면 그대로 반환
        return arr.length == 0 ? new String[] { "EMPTY" } : arr;
    }
}
