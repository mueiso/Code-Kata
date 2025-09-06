class Solution {
    
    public String solution(String my_string, String alp) {
        
        // 문자열 alp를 대문자로 바꿔서 준비
        String upper = alp.toUpperCase();
        
        // my_string 안의 alp를 upper로 치환
        return my_string.replace(alp, upper);
    }
}
