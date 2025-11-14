class Solution {
    
    public int solution(String str1, String str2) {
        
        // str2 안에 str1이 포함되어 있으면 1, 아니면 0 반환
        return str2.contains(str1) ? 1 : 0;
    }
}