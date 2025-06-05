class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (String s : babbling) {
            if (isValidPronunciation(s)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isValidPronunciation(String s) {
        int i = 0;
        boolean usedAya = false;
        boolean usedYe = false;
        boolean usedWoo = false;
        boolean usedMa = false;
        
        while (i < s.length()) {
            // Check for "aya"
            if (!usedAya && i + 3 <= s.length() && s.startsWith("aya", i)) {
                usedAya = true;
                i += 3;
            }
            // Check for "ye"
            else if (!usedYe && i + 2 <= s.length() && s.startsWith("ye", i)) {
                usedYe = true;
                i += 2;
            }
            // Check for "woo"
            else if (!usedWoo && i + 3 <= s.length() && s.startsWith("woo", i)) {
                usedWoo = true;
                i += 3;
            }
            // Check for "ma"
            else if (!usedMa && i + 2 <= s.length() && s.startsWith("ma", i)) {
                usedMa = true;
                i += 2;
            }
            // If none match, it's invalid
            else {
                return false;
            }
        }
        
        return true;
    }
}
