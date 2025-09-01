class Solution {
    
    public int solution(String myString, String pat) {

        /*
         * myString과 pat을 소문자로 변환해서 포함 여부를 확인 -> 대소문자 구분 없이 비교 가능
         * contains() 메서드는 문자열에 특정 부분 문자열이 존재하는지 확인 (있으면 true, 없으면 false)
         * 삼항 연산자를 사용하여 true일 경우 1, false일 경우 0을 반환
         */
        return myString.toLowerCase().contains(pat.toLowerCase()) ? 1 : 0;
    }
}
