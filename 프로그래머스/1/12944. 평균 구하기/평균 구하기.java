class Solution {
    
    public double solution(int[] arr) {
        
        // sum이라고 변수 선언하고 0으로 초기화
        double sum = 0;
        
        // 배열의 모든 값 더하기
        for (int num : arr) {
            sum += num;
        }
        
        // 배열의 평균값
        double answer = sum / arr.length;
        
        return answer;
    }
}