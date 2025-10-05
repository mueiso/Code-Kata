/* [문제요약]
 * arr 배열에 들어있는 수들이 무작위로 차례대로 주어진다고 가정
 * k개가 채워질 때까지 중복 없이 배열에 추가
 * 만약 arr 끝까지 봐도 k개가 안 되면, 남는 자리는 -1로 채움
 */

/* [풀이]
 1. LinkedHashSet을 사용하면 중복 제거 + 입력 순서 보장 가능
 2. arr를 앞에서부터 순회하면서, 아직 없는 수라면 Set에 추가
 3. 최종적으로 Set에 들어있는 수들을 배열로 변환
 4. 길이가 k보다 작으면 뒤에 -1 채워넣음
 */

import java.util.*;

class Solution {
    
    public int[] solution(int[] arr, int k) {
        
        // 순서 보장 + 중복 제거
        Set<Integer> set = new LinkedHashSet<>();
        
        for (int num : arr) {
            if (set.size() == k) break; // k개 채워지면 멈춤
            set.add(num);               // 중복이면 자동으로 무시
        }
        
        // 결과 배열
        int[] answer = new int[k];
        int idx = 0;
        
        for (int num : set) {
            answer[idx++] = num;
        }
        
        // 남는 부분은 -1로 채움
        while (idx < k) {
            answer[idx++] = -1;
        }
        
        return answer;
    }
}
