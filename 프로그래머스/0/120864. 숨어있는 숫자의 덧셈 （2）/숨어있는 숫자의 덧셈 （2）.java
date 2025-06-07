class Solution {
    public int solution(String my_string) {
        int answer = 0;
        StringBuilder numBuf = new StringBuilder();
        
        for (int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i);
            if (Character.isDigit(c)) {
                numBuf.append(c);
            } else {
                if (numBuf.length() > 0) {
                    answer += Integer.parseInt(numBuf.toString());
                    numBuf.setLength(0);
                }
            }
        }
        // 마지막에 버퍼에 남은 숫자가 있으면 더하기
        if (numBuf.length() > 0) {
            answer += Integer.parseInt(numBuf.toString());
        }
        
        return answer;
    }
}
