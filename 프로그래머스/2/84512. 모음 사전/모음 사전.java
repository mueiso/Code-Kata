/*
 * 사용 가능한 문자는 A, E, I, O, U → 5개
 * 단어 길이는 최대 5
 * 특정 위치에서 한 글자가 바뀌면, 그 뒤에 올 수 있는 경우의 수는 감소
 
 [자리별 가중치 계산]
 * 1번째 자리 : 5⁴ + 5³ + 5² + 5¹ + 1 = 781     // 뒤에 올 수 있는 글자 수: 최대 4자리
 * 2번째 자리 : 5³ + 5² + 5¹ + 1 = 156          // 뒤에 올 수 있는 글자 수: 최대 3자리
 * 3번째 자리 : 5² + 5¹ + 1 = 31                // 뒤에 올 수 있는 글자 수: 최대 2자리
 * 4번째 자리 : 5¹ + 1 = 6                      // 뒤에 올 수 있는 글자 수: 최대 1자리
 * 5번째 자리 : 1 = 1                           // 뒤에 올 수 있는 글자 수: 없음
 */

class Solution {
    
    public int solution(String word) {
        
        int answer = 0;

        // 각 자리의 가중치
        int[] weight = {781, 156, 31, 6, 1};
        String vowels = "AEIOU";

        for (int i = 0; i < word.length(); i++) {
            int idx = vowels.indexOf(word.charAt(i));
            answer += idx * weight[i] + 1;
        }

        return answer;
    }
}