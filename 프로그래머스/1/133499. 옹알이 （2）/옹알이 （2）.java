class Solution {
    
    public int solution(String[] babblings) {
        
        // "aya", "ye", "woo", "ma" 4가지 발음만 가능
        int answer = 0;

        for (int i = 0; i < babblings.length; i++) {

            // 1) 연속 발음(같은 발음 두 번 연속)은 불가능 → 바로 제외
            if (babblings[i].contains("ayaaya") ||
                babblings[i].contains("yeye") ||
                babblings[i].contains("woowoo") ||
                babblings[i].contains("mama")) {
                continue;
            }

            // 2) 가능한 발음들을 전부 " "로 치환
            babblings[i] = babblings[i].replace("aya", " ");
            babblings[i] = babblings[i].replace("ye", " ");
            babblings[i] = babblings[i].replace("woo", " ");
            babblings[i] = babblings[i].replace("ma", " ");

            // 3) 남은 공백도 전부 제거
            babblings[i] = babblings[i].replace(" ", "");

            // 4) 전부 치환돼서 빈 문자열이 됐다면, 전부 유효한 옹알이
            if (babblings[i].length() == 0) answer++;
        }
        return answer;
    }
}