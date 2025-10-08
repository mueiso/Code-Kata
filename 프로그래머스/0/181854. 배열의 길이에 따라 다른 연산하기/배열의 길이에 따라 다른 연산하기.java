import java.util.stream.IntStream;

class Solution {
    
    public int[] solution(int[] arr, int n) {
        
        int len = arr.length;

        return IntStream.range(0, len)
                        // 인덱스 i에 대해 조건 적용
                        .map(i -> {
                            // 홀수 길이 배열이면 짝수 인덱스만 수정
                            // 짝수 길이 배열이면 홀수 인덱스만 수정
                            if ((len % 2 == 1 && i % 2 == 0) || (len % 2 == 0 && i % 2 == 1)) {
                                return arr[i] + n;
                            } else {
                                return arr[i];
                            }
                        })
                        .toArray();
    }
}