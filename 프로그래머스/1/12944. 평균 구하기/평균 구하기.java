class Solution {
    public double solution(int[] arr) {
        double sum = 0;  // sum이라고 변수 선언하고 0으로 초기화
        
        // 배열의 모든 값 더하기
        for (int num : arr) {  // (자료형 변수명 : 배열 또는 컬렉션) -> arr의 수를 하나씩 num에 담아준다 -> 반복
            sum += num;  // sum에 현재 num 숫자 더하기
        }
        
        double answer = sum / arr.length;  // 배열의 평균값
        return answer;
    }
}