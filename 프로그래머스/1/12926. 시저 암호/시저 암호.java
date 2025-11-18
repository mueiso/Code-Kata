class Solution {
    
    public String solution(String s, int n) {

        // 문자열을 누적하기 위한 StringBuilder (+= 보다 성능 우수)
        StringBuilder sb = new StringBuilder();
        
        // 문자열을 문자 배열로 변환 후 하나씩 처리
        for (char c : s.toCharArray()) {
            
            // 공백은 암호화하지 않고 그대로 추가
            if (c == ' ') {
                sb.append(' ');
            } else {

                // 대문자인 경우 처리 ('A' ~ 'Z')
                if (Character.isUpperCase(c)) {
                    /*
                     * 'A'를 기준으로 0~25 범위로 변환 후 n만큼 이동
                     * 26으로 나눈 나머지를 구해 알파벳 순환 처리
                     * 다시 'A'를 더해 문자로 복원
                     */
                    c = (char) ((c - 'A' + n) % 26 + 'A');
                } 
                // 소문자인 경우 처리 ('a' ~ 'z')
                else {
                    // 'a' 기준으로 동일한 방식으로 순환 처리
                    c = (char) ((c - 'a' + n) % 26 + 'a');
                }

                // 변환된 문자 누적
                sb.append(c);
            }
        }

        // 완성된 암호문 반환
        return sb.toString();
    }
}