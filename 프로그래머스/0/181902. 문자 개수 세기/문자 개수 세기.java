class Solution {
    
    public int[] solution(String my_string) {
        
        // 알파벳 개수를 저장할 배열 (A~Z, a~z 순서)
        int[] answer = new int[52];
        
        // 문자열의 각 문자를 순회
        for (int i = 0; i < my_string.length(); i++) {
            char c = my_string.charAt(i);
            
            if (c >= 'A' && c <= 'Z') {
                // 대문자일 경우 0~25 인덱스에 기록
                answer[c - 'A']++;
            } else if (c >= 'a' && c <= 'z') {
                // 소문자일 경우 26~51 인덱스에 기록
                answer[c - 'a' + 26]++;
            }
        }
        
        return answer;
    }
}
