class Solution {
    
    public String solution(int n, int t, int m, int p) {
        
        StringBuilder gameStream = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        int num = 0;
        // 1. 튜브가 t개를 모두 구할 수 있을 때까지 전체 진법 문자열을 생성합니다.
        while (gameStream.length() < t * m) {
            // Integer.toString(숫자, 진법)을 사용하면 아주 쉽게 n진법 변환이 가능합니다.
            // 10~15는 대문자 A~F로 출력해야 하므로 toUpperCase()를 붙여줍니다.
            gameStream.append(Integer.toString(num, n).toUpperCase());
            num++;
        }
        
        // 2. 전체 문자열에서 튜브의 순서(p)에 해당하는 글자만 t개 추출합니다.
        // 튜브의 i번째 턴의 인덱스 공식: i * m + (p - 1)
        for (int i = 0; i < t; i++) {
            int targetIndex = i * m + (p - 1);
            answer.append(gameStream.charAt(targetIndex));
        }
        
        return answer.toString();
    }
}