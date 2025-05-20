class Solution {
    public long[] solution(int x, int n) {
        // 결과 배열의 크기는 n
        long[] answer = new long[n];
        
         // x부터 시작해서 x씩 증가하는 수열을 만들어 배열에 저장
        for (int i = 0; i < n; i++) {
            answer[i] = (long) x * (i + 1); // x*(i+1)로 증가하는 값 저장
        }
        return answer;
    }
}