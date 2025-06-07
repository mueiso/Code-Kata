import java.util.Arrays;

class Solution {
    public int solution(String[] spell, String[] dic) {
        // spell 배열을 하나의 문자열로 만들고, 정렬된 상태로 저장
        char[] spellChars = String.join("", spell).toCharArray();
        Arrays.sort(spellChars);
        String sortedSpell = new String(spellChars);
        
        for (String word : dic) {
            if (word.length() != spell.length) continue;
            char[] wordChars = word.toCharArray();
            Arrays.sort(wordChars);
            String sortedWord = new String(wordChars);
            
            if (sortedSpell.equals(sortedWord)) {
                return 1;  // 일치하는 단어가 존재함
            }
        }
        
        return 2;  // 일치하는 단어가 없음
    }
}
