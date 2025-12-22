import java.util.*;

class Solution {
    
    public String solution(String[] survey, int[] choices) {

        // 각 성격 유형 점수 저장
        Map<Character, Integer> score = new HashMap<>();
        for (char c : new char[]{'R','T','C','F','J','M','A','N'}) {
            score.put(c, 0);
        }

        for (int i = 0; i < survey.length; i++) {
            char left = survey[i].charAt(0);   // 비동의 쪽
            char right = survey[i].charAt(1);  // 동의 쪽
            int choice = choices[i];

            if (choice < 4) {
                // 1(3점), 2(2점), 3(1점) → 왼쪽 타입에 점수
                score.put(left, score.get(left) + (4 - choice));
            } else if (choice > 4) {
                // 5(1점), 6(2점), 7(3점) → 오른쪽 타입에 점수
                score.put(right, score.get(right) + (choice - 4));
            }
            // choice == 4면 점수 없음
        }

        // 각 지표별로 점수 비교(동점이면 사전순 빠른 글자)
        StringBuilder result = new StringBuilder();

        result.append(pick(score, 'R', 'T'));
        result.append(pick(score, 'C', 'F'));
        result.append(pick(score, 'J', 'M'));
        result.append(pick(score, 'A', 'N'));

        return result.toString();
    }

    private char pick(Map<Character, Integer> score, char a, char b) {
        int sa = score.get(a);
        int sb = score.get(b);

        if (sa > sb) return a;
        if (sb > sa) return b;
        return (a < b) ? a : b; // 동점이면 사전순 빠른 것
    }
}