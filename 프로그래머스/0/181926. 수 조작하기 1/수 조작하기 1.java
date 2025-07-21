class Solution {
    
    public int solution(int n, String control) {
        
        int answer = n;

        // control 문자열을 문자배열로 변환하여 하나씩 문자 c로 반복 (예: "wsd" → ['w', 's', 'd'])
        for(char c : control.toCharArray()) {
            switch(c) {
                case 'w': answer += 1;
                    break;
                case 's': answer -= 1;
                    break;
                case 'd': answer += 10;
                    break;
                case 'a': answer -= 10;
                    break;
                default:break;
            }
        }

        return answer;
    }
}