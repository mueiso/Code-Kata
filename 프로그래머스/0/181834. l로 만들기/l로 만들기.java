class Solution {
    
    public String solution(String myString) {
        
        // 'l'부터 'z'가 아닌 문자(a~k)를 모두 'l'로 치환
        return myString.replaceAll("[^l-z]", "l");
    }
}