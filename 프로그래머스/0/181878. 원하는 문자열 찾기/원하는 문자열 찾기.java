class Solution {
    
    public int solution(String myString, String pat) {
        
        // myString과 pat을 소문자로 변환 (대소문자 구분하지 않기 위해)
        String lowerMyString = myString.toLowerCase();
        String lowerPat = pat.toLowerCase();
        
        // 부분 문자열 존재 여부 확인
        if (lowerMyString.contains(lowerPat)) {
            return 1;
        } else {
            return 0;
        }
    }
}
