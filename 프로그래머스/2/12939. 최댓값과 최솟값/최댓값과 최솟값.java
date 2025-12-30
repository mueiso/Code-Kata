class Solution {
    
    public String solution(String s) {

        // 공백 기준으로 문자열 분리
        String[] nums = s.split(" ");

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // 각 숫자를 정수로 변환하며 최솟값, 최댓값 갱신
        for (String num : nums) {
            int value = Integer.parseInt(num);
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        // "최솟값 최댓값" 형태로 반환
        return min + " " + max;
    }
}