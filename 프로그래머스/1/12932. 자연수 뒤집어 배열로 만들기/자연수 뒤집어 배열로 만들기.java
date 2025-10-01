class Solution {
    
    public int[] solution(long n) {
        
        // 1. 숫자 n을 문자열로 변환
        //    예: n = 12345 → "12345"
        return new StringBuilder(Long.toString(n))
                    
                    // 2. 문자열 뒤집기
                    //    "12345" → "54321"
                    .reverse()
                    
                    // 3. 다시 문자열로 변환
                    .toString()
                    
                    // 4. 각 문자를 IntStream으로 변환
                    //    "54321" → ['5','4','3','2','1']
                    .chars()
                    
                    // 5. 문자 '0'을 빼서 실제 숫자로 변환
                    //    '5' - '0' = 5
                    .map(c -> c - '0')
                    
                    // 6. 최종적으로 int[] 배열로 반환
                    .toArray();
    }
}
