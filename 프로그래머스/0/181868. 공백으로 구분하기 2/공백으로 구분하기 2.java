class Solution {
    
    public String[] solution(String my_string) {
        
        // "\\s+" : 공백(스페이스, 탭 등)을 1개 이상 기준으로 나눔
        return my_string.trim().split("\\s+");
    }
}
