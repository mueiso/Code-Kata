class Solution {
    
    public int solution(int[] a, int[] b) {
        
        // 내적 합계를 저장할 변수 초기화
        int answer = 0;
        
        // 두 배열의 각 인덱스를 순회하며 같은 위치의 원소끼리 곱해서 더함
        for (int i = 0; i < a.length; i++) {
            answer += a[i] * b[i];  // a[i] * b[i]의 결과를 누적
        }
        
        return answer; // 최종 내적 결과 반환
    }
}
