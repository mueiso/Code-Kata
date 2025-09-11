class Solution {
    
    public String[] solution(String my_string) {

        /*
         * 1. trim() : 문자열의 앞뒤에 있는 불필요한 공백을 제거
         * 예) "   hello world   " → "hello world"
         */

        /*
         * 2. split("[ ]+") : 정규식을 사용하여 공백을 기준으로 문자열을 나눔
         * [ ]   → '스페이스 공백' 하나
         * +     → 하나 이상 반복
         * 즉, "공백이 1개 이상"인 부분을 모두 구분자로 인식해서 단어 분리
         */

        // 최종적으로 공백이 여러 개 있어도 단어만 배열에 담기게 됨
        return my_string.trim().split("[ ]+");
    }
}
