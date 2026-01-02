class Solution {
    
    public int[] solution(String s) {
        
        int[] answer = new int[2]; // [이진 변환 횟수, 제거된 0의 개수]
        int temp;

        // s가 "1"이 될 때까지 반복
        while (!s.equals("1")) {

            // 현재 문자열 길이를 제거된 0 계산을 위해 더해둠
            answer[1] += s.length();

            // 0 제거
            s = s.replaceAll("0", "");

            // 0 제거 후 길이
            temp = s.length();

            // 길이를 2진수 문자열로 변환
            s = Integer.toBinaryString(temp);

            // 변환 횟수 증가
            answer[0]++;

            // 제거되지 않은 1의 개수만큼 빼서 실제 제거된 0 개수 계산
            answer[1] -= temp;
        }

        return answer;
    }
}