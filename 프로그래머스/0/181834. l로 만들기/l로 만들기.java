class Solution {
    
    public String solution(String myString) {

        StringBuilder sb = new StringBuilder();

        for (char c : myString.toCharArray()) {
            if (c < 'l') {
                sb.append('l');   // 'l'보다 앞선 문자 → 'l'
            } else {
                sb.append(c);     // 그대로 유지
            }
        }

        return sb.toString();
    }
}