class Solution {
    
    public String solution(String my_string, int n) {
        
        // 문자열의 길이에서 n을 뺀 위치부터 끝까지 잘라냄
        return my_string.substring(my_string.length() - n);
    }
}
