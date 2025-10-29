import java.util.stream.IntStream;

class Solution {
    
    public int solution(int[] a, int[] b) {
        
        return IntStream.range(0, a.length)
                        .map(i -> a[i] * b[i]) // 같은 인덱스끼리 곱하기
                        .sum();                // 합계 계산
    }
}