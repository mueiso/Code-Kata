import java.util.stream.IntStream;

class Solution {
    
    public int[] solution(int n) {
        
        return IntStream.concat(
            
                // 콜라츠 수열을 생성하는 IntStream
                IntStream.iterate(
                        n,                       // 시작값 (초기값 n)
                        i -> i > 1,              // 종료 조건: 1보다 큰 동안 반복
                        i -> i % 2 == 0          // 다음 값 계산:
                             ? i / 2             // 짝수이면 2로 나눔
                             : i * 3 + 1         // 홀수이면 3을 곱하고 1을 더함
                ),
            
                // 위 반복이 i == 1 에서 종료되므로, 마지막 1을 따로 스트림에 추가
                IntStream.of(1)
        )
            
        // IntStream을 배열로 변환하여 반환
        .toArray();
    }
}
