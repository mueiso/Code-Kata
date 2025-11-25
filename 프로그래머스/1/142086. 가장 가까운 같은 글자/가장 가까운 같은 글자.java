class Solution {
    
    public int[] solution(String str) {

        // 정답을 저장할 배열 (각 인덱스별로 거리 저장)
        int[] result = new int[str.length()];

        // 문자열의 각 인덱스 i를 순회
        for (int i = 0; i < str.length(); i++) {

            // 0 ~ i-1 구간의 부분 문자열
            String subStr = str.substring(0, i);

            // 현재 문자
            char current = str.charAt(i);

            // 부분 문자열에서 현재 문자가 등장하지 않은 경우
            if (subStr.indexOf(current) == -1) {
                result[i] = -1;
            } 
            // 이전에 등장한 적이 있는 경우
            else {
                // 가장 최근에 등장한 위치(lastIndexOf)와의 거리 계산
                result[i] = i - subStr.lastIndexOf(current);
            }
        }

        return result;
    }
}