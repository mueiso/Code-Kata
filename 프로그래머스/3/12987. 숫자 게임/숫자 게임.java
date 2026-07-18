import java.util.Arrays;

class Solution {
    
    public int solution(int[] A, int[] B) {
        
        int answer = 0;
        
        // 1. 두 팀의 숫자 배열을 모두 오름차순으로 정렬합니다.
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx = 0; // A팀 카드를 가리키는 포인터
        int bIdx = 0; // B팀 카드를 가리키는 포인터
        
        // 2. B팀의 모든 카드를 순회할 때까지 반복합니다.
        while (bIdx < B.length) {
            // B팀의 카드가 A팀의 현재 최약체 카드보다 크다면 승리 가능!
            if (B[bIdx] > A[aIdx]) {
                answer++; // 승점 획득
                aIdx++;   // 다음으로 작은 A팀 카드를 타겟으로 변경
            }
            
            // 이겼든, 졌든 B팀은 현재 카드를 사용했으므로 다음 카드로 이동
            bIdx++;
        }
        
        return answer;
    }
}