import java.util.stream.IntStream;

class Solution {
    
    public int solution(int[] arr, int idx) {

        /*
         * IntStream.range(start, end) → start(포함)부터 end(미포함)까지의 연속된 정수 스트림을 생성한다
         * 여기서는 idx부터 arr.length - 1 까지 인덱스 스트림을 만든다
         */
        return IntStream.range(idx, arr.length)

                        /*
                         * filter(predicate) → 조건을 만족하는 값만 통과시킨다
                         * 여기서는 arr[i] == 1 인 인덱스만 남긴다
                         */
                        .filter(i -> arr[i] == 1)
                        
                        // findFirst() → 조건을 만족하는 값 중에서 가장 앞에 있는 값(OptionalInt)을 가져온다
                        .findFirst()
                        
                        // orElse(-1) → 만약 조건을 만족하는 값이 없으면 -1을 반환한다
                        .orElse(-1);
    }
}
