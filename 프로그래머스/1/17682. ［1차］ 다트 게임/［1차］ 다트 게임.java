class Solution {
    
    public int solution(String dartResult) {
        
        int[] scores = new int[3]; // 3번의 기회 점수를 저장할 배열
        int idx = -1;              // 현재 기회(0, 1, 2)를 가리키는 인덱스
        
        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            
            // 1. 점수(숫자) 처리
            if (Character.isDigit(c)) {
                idx++; // 새로운 라운드 진입
                
                // 다음 글자까지 확인해서 '10'인지 체크하는 핵심 로직
                if (c == '1' && i + 1 < dartResult.length() && dartResult.charAt(i + 1) == '0') {
                    scores[idx] = 10;
                    i++; // '0' 글자는 처리했으므로 인덱스를 한 칸 건너뜀
                } else {
                    scores[idx] = c - '0';
                }
            } 
            // 2. 보너스(S, D, T) 처리
            else if (c == 'S' || c == 'D' || c == 'T') {
                if (c == 'D') {
                    scores[idx] = (int) Math.pow(scores[idx], 2);
                } else if (c == 'T') {
                    scores[idx] = (int) Math.pow(scores[idx], 3);
                }
                // 'S'는 1제곱이므로 점수 변동이 없어 생략 가능합니다.
            } 
            // 3. 옵션(*, #) 처리
            else if (c == '*' || c == '#') {
                if (c == '*') {
                    scores[idx] *= 2;       // 해당 라운드 점수 2배
                    if (idx > 0) {
                        scores[idx - 1] *= 2; // 직전 라운드 점수도 2배 (존재할 때만)
                    }
                } else if (c == '#') {
                    scores[idx] *= -1;      // 해당 라운드 점수 마이너스
                }
            }
        }
        
        // 3개 라운드의 최종 합산 점수 구하기
        int answer = 0;
        for (int score : scores) {
            answer += score;
        }
        
        return answer;
    }
}