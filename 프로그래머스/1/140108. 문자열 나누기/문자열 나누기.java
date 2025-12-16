class Solution {
    
    public int solution(String s) {
        
        int answer = 0;

        char x = 0;          // 현재 덩어리의 기준 문자
        int countX = 0;      // x와 같은 문자 개수
        int countOther = 0;  // x가 아닌 문자 개수

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 새 덩어리 시작(아직 기준 문자가 없으면 첫 글자를 기준으로)
            if (countX == 0 && countOther == 0) {
                x = c;
            }

            // x와 같으면 countX, 다르면 countOther 증가
            if (c == x) {
                countX++;
            } else countOther++;

            // 두 개수가 같아지는 순간 덩어리 하나 분리
            if (countX == countOther) {
                answer++;
                countX = 0;
                countOther = 0;
            }
        }

        // 마지막 덩어리가 남아있다면(끝까지 갔는데 같아지지 않은 경우)
        if (countX != 0 || countOther != 0) {
            answer++;
        }

        return answer;
    }
}