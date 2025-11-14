public class Solution {
    
    public int solution(String t, String p) {
        
        int count = 0;
        int pLength = p.length();
        long pNum = Long.parseLong(p); // p를 long으로 변환

        for (int i = 0; i <= t.length() - pLength; i++) {
            String sub = t.substring(i, i + pLength); // 부분 문자열 추출
            long subNum = Long.parseLong(sub);       // 부분 문자열을 long으로 변환

            if (subNum <= pNum) {
                count++;
            }
        }

        return count;
    }
}