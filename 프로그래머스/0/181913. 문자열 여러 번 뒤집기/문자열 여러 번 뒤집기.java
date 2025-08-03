class Solution {
    
    public String solution(String my_string, int[][] queries) {
        
        // StringBuilder는 문자열을 수정할 수 있는 클래스이므로, 문자열 뒤집기 작업에 적합
        StringBuilder sb = new StringBuilder(my_string);

        // queries 배열의 각 명령을 순서대로 처리
        for (int[] query : queries) {
            int s = query[0]; // 시작 인덱스
            int e = query[1]; // 끝 인덱스

            // 부분 문자열을 뒤집기 위해, 현재 구간의 문자열을 추출 후 reverse
            String reversed = new StringBuilder(sb.substring(s, e + 1)).reverse().toString();

            // 뒤집은 문자열을 원래 위치에 다시 삽입
            for (int i = 0; i < reversed.length(); i++) {
                sb.setCharAt(s + i, reversed.charAt(i));
            }
        }

        // 최종 결과 문자열 반환
        return sb.toString();
    }
}
