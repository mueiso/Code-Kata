class Solution {
    public String solution(String rsp) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < rsp.length(); i++) {
            char c = rsp.charAt(i);
            if (c == '2') {         // 가위 → 바위(0)
                answer.append('0');
            } else if (c == '0') {  // 바위 → 보(5)
                answer.append('5');
            } else if (c == '5') {  // 보 → 가위(2)
                answer.append('2');
            }
        }
        return answer.toString();
    }
}
