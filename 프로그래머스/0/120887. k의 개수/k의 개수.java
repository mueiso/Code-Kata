class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        String target = String.valueOf(k); // k를 문자열로 변환

        for (int num = i; num <= j; num++) {
            String strNum = String.valueOf(num);
            for (int idx = 0; idx < strNum.length(); idx++) {
                if (strNum.charAt(idx) == target.charAt(0)) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
