class Solution {
    
    public int solution(String[] babbling) {
        
        int answer = 0;

        String[] words = {"aya", "ye", "woo", "ma"};

        for (String b : babbling) {
            if (canPronounce(b, words)) {
                answer++;
            }
        }

        return answer;
    }

    // 해당 문자열이 규칙에 맞게 발음 가능한지 검사
    private boolean canPronounce(String s, String[] words) {

        int idx = 0;
        String prev = "";  // 직전에 사용한 발음

        while (idx < s.length()) {
            boolean matched = false;

            for (String w : words) {
                // s의 idx 위치에서 w로 시작하는지 확인
                if (s.startsWith(w, idx)) {

                    // 바로 이전 발음과 같으면 연속 발음이라서 실패
                    if (w.equals(prev)) {
                        return false;
                    }

                    // 이 발음을 사용
                    prev = w;
                    idx += w.length(); // 그만큼 건너뛰기
                    matched = true;
                    break;
                }
            }

            // 네 가지 발음 중 어느 것도 매칭되지 않으면 발음 불가
            if (!matched) {
                return false;
            }
        }

        // 끝까지 규칙대로 잘 잘려 나갔으면 발음 가능
        return true;
    }
}