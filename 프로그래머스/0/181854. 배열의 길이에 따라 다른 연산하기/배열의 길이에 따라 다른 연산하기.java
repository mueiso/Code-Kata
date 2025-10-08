class Solution {
    
    public int[] solution(int[] arr, int n) {
        
        int len = arr.length;
        
        // arr 복사 (원본 배열 수정 방지)
        int[] answer = arr.clone();
        
        // 홀수 길이 → 짝수 인덱스 수정
        // 짝수 길이 → 홀수 인덱스 수정
        int startIndex = (len % 2 == 1) ? 0 : 1;

        for (int i = startIndex; i < len; i += 2) {
            answer[i] += n;
        }
        
        return answer;
    }
}