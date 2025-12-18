class Solution {
    
    public String solution(String s, String skip, int index) {

        // skip 여부 표시 (a~z)
        boolean[] isSkip = new boolean[26];
        for (char c : skip.toCharArray()) {
            isSkip[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            int cur = ch - 'a';
            int moved = 0;

            // 유효한(스킵 아닌) 문자로 index번 이동
            while (moved < index) {
                cur = (cur + 1) % 26;          // 다음 알파벳(z면 a로)
                if (isSkip[cur]) continue;     // skip이면 카운트하지 않고 건너뜀
                moved++;                       // skip이 아니면 1칸 이동으로 인정
            }

            sb.append((char) ('a' + cur));
        }

        return sb.toString();
    }
}