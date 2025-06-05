import java.util.HashSet;

class Solution {
    public String solution(String my_string) {
        StringBuilder answer = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();
        
        for (char ch : my_string.toCharArray()) {
            if (!seen.contains(ch)) {
                seen.add(ch);
                answer.append(ch);
            }
        }
        
        return answer.toString();
    }
}
