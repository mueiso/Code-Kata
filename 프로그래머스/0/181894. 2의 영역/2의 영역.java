import java.util.stream.IntStream;

class Solution {
    
    public int[] solution(int[] arr) {
        
        /*
         * arr 배열에서 값이 2인 요소의 인덱스들을 모두 뽑아 배열로 저장
         * 예) arr = [1, 2, 3, 2, 4] → idx = [1, 3]
         
         * IntStream.range(0, arr.length): 배열 인덱스를 0부터 끝까지 생성
         * .filter(i -> arr[i] == 2): 값이 2인 인덱스만 걸러냄
         */
        int[] idx = IntStream.range(0, arr.length)
                             .filter(i -> arr[i] == 2)
                             .toArray();
        
        // 만약 2가 하나도 없다면 [-1] 반환
        if (idx.length == 0) return new int[]{-1};
        
        /*
         * arr에서 첫 번째 2의 위치(idx[0])부터 마지막 2의 위치(idx[idx.length - 1])까지의 부분 배열을 추출
         * 예) arr = [1, 2, 3, 2, 4], idx = [1, 3] → arr[1..3] = [2, 3, 2]
         
         * rangeClosed(첫번째 2, 마지막 2): 두 값 사이 구간 인덱스 전부 뽑음
         * .map(i -> arr[i]): 해당 인덱스에 있는 실제 값으로 변환
         */
        return IntStream.rangeClosed(idx[0], idx[idx.length - 1])
                        .map(i -> arr[i])
                        .toArray();
    }
}
