class Solution {
    
    public int[] solution(String s) {
        
        int transformCount = 0; // 이진 변환 횟수
        int removedZeroCount = 0; // 제거된 0의 총 개수

        // s가 "1"이 될 때까지 반복
        while (!s.equals("1")) {
            transformCount++;

            int beforeLen = s.length();      // 변환 전 길이
            s = s.replace("0", "");          // 0 제거
            int afterLen = s.length();       // 0 제거 후 길이

            removedZeroCount += (beforeLen - afterLen); // 이번에 제거된 0 누적

            // 남은 문자열 길이를 2진수 문자열로 변환
            s = Integer.toBinaryString(afterLen);
        }

        return new int[]{transformCount, removedZeroCount};
    }
}