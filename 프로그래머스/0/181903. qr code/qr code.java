class Solution {
    
    public String solution(int q, int r, String code) {
        
        // StringBuilder를 사용해서 문자열 연결 성능 최적화
        StringBuilder sb = new StringBuilder();
        
        // code 문자열의 각 문자 인덱스를 순회
        for (int i = 0; i < code.length(); i++) {
            
            // i % q가 r과 같으면 해당 문자를 추가
            if (i % q == r) {
                sb.append(code.charAt(i));
            }
        }
        
        // StringBuilder를 String으로 변환해서 반환
        return sb.toString();
    }
}
