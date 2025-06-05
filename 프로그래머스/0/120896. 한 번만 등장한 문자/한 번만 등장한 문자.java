import java.util.Arrays;

class Solution {
    public String solution(String s) {
        int[] freq = new int[26];
        
        // 1. 각 문자 빈도 계산
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // 2. 빈도가 1인 문자만 골라서 배열에 담음
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 1) {
                sb.append((char) (i + 'a'));
            }
        }
        
        // 3. StringBuilder에 이미 알파벳 순으로 들어갔으므로 그대로 반환
        return sb.toString();
    }
}
