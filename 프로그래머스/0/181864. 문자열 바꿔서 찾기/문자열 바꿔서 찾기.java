class Solution {
    
    public int solution(String myString, String pat) {
        
        /*
         * 1. "A"를 "a"로 바꿔서 임시 처리 (대소문자 구분을 활용해 충돌 방지)
               예) "AB" → "aB"
         */
        myString = myString.replace("A", "a")
                           /*
                            * 2. "B"를 "A"로 변경
                                  예) "aB" → "aA"
                            */
                           .replace("B", "A")
                           /*
                            * 3. 임시 처리했던 "a"를 최종적으로 "B"로 변경
                                 예) "aA" → "BA"
                            */
                           .replace("a", "B");
        
        // 4. 변환된 문자열에 pat이 포함되어 있으면 1, 없으면 0 반환
        return myString.contains(pat) ? 1 : 0;
    }
}
