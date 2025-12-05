class Solution {
    
    public String[] solution(String[] picture, int k) {

        String[] answer = new String[picture.length * k];

        int idx = 0;

        for (String row : picture) {

            // 가로 k배 확장
            StringBuilder sb = new StringBuilder();
            for (char c : row.toCharArray()) {
                sb.append(String.valueOf(c).repeat(k));
            }
            String expandedRow = sb.toString();

            // 세로 k배 확장
            for (int i = 0; i < k; i++) {
                answer[idx++] = expandedRow;
            }
        }

        return answer;
    }
}