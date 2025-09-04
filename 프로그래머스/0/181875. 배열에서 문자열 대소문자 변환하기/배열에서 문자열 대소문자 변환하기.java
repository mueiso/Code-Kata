class Solution {
    
    public String[] solution(String[] strArr) {
        
        // 결과를 담을 배열 생성 (원본과 같은 크기)
        String[] answer = new String[strArr.length];
        
        for (int i = 0; i < strArr.length; i++) {
            if (i % 2 == 0) {
                // 짝수 인덱스 → 소문자 변환
                answer[i] = strArr[i].toLowerCase();
            } else {
                // 홀수 인덱스 → 대문자 변환
                answer[i] = strArr[i].toUpperCase();
            }
        }
        
        return answer;
    }
}
