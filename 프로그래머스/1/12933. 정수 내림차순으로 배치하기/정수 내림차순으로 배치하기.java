/* [문제풀이]
 * String.valueOf(n) → 숫자를 문자열로 바꿈
   → "118372"

 * .toCharArray() → ['1','1','8','3','7','2']

 * Arrays.sort(digits) → 오름차순 정렬
   → ['1','1','2','3','7','8']

 * new StringBuilder(...).reverse() → 내림차순 변환
   → "873211"

 * Long.parseLong() → 숫자로 변환
   → 873211
 */

import java.util.Arrays;

class Solution {
    
    public long solution(long n) {
        
        // 1. 숫자를 문자열로 변환 후 문자 배열로 변환
        char[] digits = String.valueOf(n).toCharArray();

        // 2. 오름차순 정렬
        Arrays.sort(digits);

        // 3. 내림차순으로 뒤집기 (StringBuilder.reverse() 사용)
        String reversed = new StringBuilder(new String(digits)).reverse().toString();

        // 4. 다시 long 타입으로 변환 후 반환
        return Long.parseLong(reversed);
    }
}