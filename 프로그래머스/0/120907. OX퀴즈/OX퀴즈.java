class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for (int i = 0; i < quiz.length; i++) {
            String[] parts = quiz[i].split(" ");
            
            int x = Integer.parseInt(parts[0]);
            String operator = parts[1];
            int y = Integer.parseInt(parts[2]);
            int result = Integer.parseInt(parts[4]);

            int calculated = operator.equals("+") ? x + y : x - y;

            answer[i] = (calculated == result) ? "O" : "X";
        }

        return answer;
    }
}
