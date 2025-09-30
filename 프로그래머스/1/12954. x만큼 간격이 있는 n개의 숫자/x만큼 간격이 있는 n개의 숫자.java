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

/*
 * 배열 선언
   long[] answer = new long[n];
   → 결과로 반환할 배열의 길이는 n이므로 미리 할당

 * for문 반복
   i = 0부터 n-1까지 반복하면서, 각 칸에 (long) x * (i+1)을 넣음

   i=0 → x*1
   i=1 → x*2
   i=2 → x*3
   ...
   i=n-1 → x*n

  * 형 변환
    (long) x * (i+1)
    → int의 곱셈 결과가 int 범위를 넘어갈 수 있으므로 long으로 변환 후 저장

  * 배열 반환
    모든 값이 채워진 answer 배열을 반환
 */