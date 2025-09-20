class Solution {
    
    public int solution(String myString, String pat) {
        
        // 1. myString의 "A" ↔ "B" 변환
        StringBuilder sb = new StringBuilder();
        for (char c : myString.toCharArray()) {
            if (c == 'A') {
                sb.append('B');
            } else {
                sb.append('A');
            }
        }
        
        // 2. 변환된 문자열 생성
        String converted = sb.toString();
        
        // 3. pat이 포함되는지 확인
        return converted.contains(pat) ? 1 : 0;
    }
}
