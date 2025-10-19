/* [문제풀이]
 * "12345" → .chars() → [49, 50, 51, 52, 53] (문자 코드값)
 * .map(ch -> ch - '0') → [1, 2, 3, 4, 5]
 * .sum() → 15
 */

class Solution {
    
    public int solution(String num_str) {
        
        // 문자열의 각 문자를 숫자로 변환한 뒤 모두 더함
        return num_str.chars()          // 문자열을 문자 코드 스트림으로 변환
                      .map(ch -> ch - '0') // '0'을 빼서 실제 숫자로 변환
                      .sum();              // 모든 숫자의 합 계산
    }
}
