class Solution {
    public int solution(String A, String B) {
        int len = A.length();
        
        // A를 k번 우측으로 민 결과가 B인지 확인
        for (int k = 0; k < len; k++) {
            // 우측으로 k번 민 문자열 생성:
            // 마지막 k글자를 앞에 붙이고 나머지를 뒤에 붙임
            String rotated = A.substring(len - k) + A.substring(0, len - k);
            if (rotated.equals(B)) {
                return k;
            }
        }
        
        return -1;  // 어떤 k로도 B가 되지 않으면 -1 반환
    }
}
