class Solution {
    
    public double solution(int[] arr) {
        
        // 배열이 비어 있는 경우 처리
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("배열이 비어있습니다."); 
        }

        // 합계 저장
        double sum = 0;
        for (int num : arr) {
            sum += num;
        }
        // 평균 계산
        double answer = sum / arr.length;
        
        return answer;
    }
}
