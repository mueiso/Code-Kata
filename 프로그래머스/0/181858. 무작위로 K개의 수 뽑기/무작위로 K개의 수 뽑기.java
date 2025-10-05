/* [문제요약]
 * arr 배열에 들어있는 수들이 무작위로 차례대로 주어진다고 가정
 * k개가 채워질 때까지 중복 없이 배열에 추가
 * 만약 arr 끝까지 봐도 k개가 안 되면, 남는 자리는 -1로 채움
*/

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    
    public int[] solution(int[] arr, int k) {
        
        return IntStream
                // arr 배열을 중복 제거(distinct)해서 스트림으로 변환
                .concat(
                    Arrays.stream(arr).distinct(),
                    
                    // 무한히 -1을 내보내는 스트림 생성
                    // → arr에서 얻은 고유 원소가 부족할 경우, 뒤를 -1로 채움
                    IntStream.generate(() -> -1)
                )

                // k개까지만 잘라냄
                // → arr에 distinct 원소가 k개 이상이면 앞 k개만, 부족하면 -1을 가져와서 k개 채움
                .limit(k)

                // 최종적으로 int[] 배열로 변환
                .toArray();
    }
}