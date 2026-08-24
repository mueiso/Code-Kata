class Solution {
    
    public String[] solution(String[] s) {
        
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            StringBuilder sb = new StringBuilder();
            int count = 0;

            // 1. "110" 추출
            for (int j = 0; j < str.length(); j++) {
                sb.append(str.charAt(j));
                int len = sb.length();

                if (len >= 3 
                    && sb.charAt(len - 3) == '1' 
                    && sb.charAt(len - 2) == '1' 
                    && sb.charAt(len - 1) == '0') {
                    count++;
                    sb.setLength(len - 3); // O(1)에 삭제 처리
                }
            }

            // 2. 추출한 "110"들을 모은 문자열 생성
            StringBuilder pattern = new StringBuilder();
            for (int j = 0; j < count; j++) {
                pattern.append("110");
            }

            // 3. 마지막 '0' 위치 찾기
            int lastZero = sb.lastIndexOf("0");

            // 4. 최적의 위치에 삽입
            if (lastZero == -1) {
                sb.insert(0, pattern);
            } else {
                sb.insert(lastZero + 1, pattern);
            }

            answer[i] = sb.toString();
        }

        return answer;
    }
}