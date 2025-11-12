public class Solution {
    
    public String solution(String s) {
        
        StringBuilder answer = new StringBuilder();
        String[] words = s.split(" ", -1); // 공백 유지
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                if (j % 2 == 0) {
                    answer.append(Character.toUpperCase(ch));
                } else {
                    answer.append(Character.toLowerCase(ch));
                }
            }
            if (i < words.length - 1) {
                answer.append(" "); // 단어 사이의 공백 추가
            }
        }
        
        return answer.toString();
    }
}