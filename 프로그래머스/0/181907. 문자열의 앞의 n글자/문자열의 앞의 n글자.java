class Solution {
    
    public String solution(String my_string, int n) {
        
        // substring(시작 인덱스, 끝 인덱스) → 시작은 포함, 끝은 미포함
        // 앞에서 n글자를 잘라내기 위해 (0, n) 사용
        return my_string.substring(0, n);
    }
}
