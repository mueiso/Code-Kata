class Solution {
    
    public String solution(String[] my_strings, int[][] parts) {
        
        // 결과 문자열을 효율적으로 만들기 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder();

        // 각 문자열과 해당 부분 인덱스를 차례로 처리
        for (int i = 0; i < my_strings.length; i++) {
            int start = parts[i][0]; // 시작 인덱스
            int end = parts[i][1];   // 끝 인덱스

            // 문자열의 start부터 end까지 부분 문자열 추출 (end 포함이므로 +1)
            String part = my_strings[i].substring(start, end + 1);

            // 결과 문자열에 이어 붙이기
            sb.append(part);
        }

        // 최종 문자열 반환
        return sb.toString();
    }
}
