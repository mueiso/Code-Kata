class Solution {
    public int solution(String s) {
        
        // 문자열 길이가 1인 경우 예외 처리
        if (s.length() == 1) return 1;

        int answer = s.length();

        // 1. 단위를 1부터 문자열 길이의 절반까지 늘려가며 확인
        for (int i = 1; i <= s.length() / 2; i++) {
            StringBuilder compressed = new StringBuilder();
            String prev = s.substring(0, i); // 첫 번째 단위 문자열
            int count = 1;

            // 2. 단위(i)만큼 이동하며 문자열 비교
            for (int j = i; j <= s.length(); j += i) {
                // 마지막 부분 처리: 남은 길이가 단위보다 작으면 끝까지 가져옴
                int endIdx = Math.min(j + i, s.length());
                String curr = s.substring(j, endIdx);

                if (prev.equals(curr)) {
                    count++;
                } else {
                    // 이전과 다르면 압축 문자열 생성
                    if (count >= 2) compressed.append(count);
                    compressed.append(prev);
                    
                    prev = curr; // 기준 변경
                    count = 1;
                }
            }
            
            // 3. 루프 종료 후 남아있는 문자열 붙이기
            compressed.append(prev);
            
            // 가장 짧은 길이로 갱신
            answer = Math.min(answer, compressed.length());
        }

        return answer;
    }
}