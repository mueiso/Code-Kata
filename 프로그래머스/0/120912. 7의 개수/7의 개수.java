class Solution {
    public int solution(int[] array) {
        int answer = 0;
        
        for(int num : array) {
            // 숫자를 문자열로 바꾸고 각 자리 숫자 검사
            String str = String.valueOf(num);
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '7') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
