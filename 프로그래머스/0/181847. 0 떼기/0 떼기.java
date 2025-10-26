class Solution {
    
    public String solution(String n_str) {
        
        // 정규표현식을 사용하여 문자열의 맨 앞에 있는 모든 0 제거
        return n_str.replaceFirst("^0+", "");
    }
}
