class Solution {
    public String solution(int age) {
        StringBuilder sb = new StringBuilder();
        String ageStr = String.valueOf(age);
        
        for (char digit : ageStr.toCharArray()) {
            sb.append((char) ('a' + (digit - '0')));
        }
        
        return sb.toString();
    }
}
