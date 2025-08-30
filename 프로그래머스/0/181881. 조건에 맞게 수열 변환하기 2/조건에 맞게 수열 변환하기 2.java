import java.util.Arrays;

class Solution {
    
    public int solution(int[] arr) {
        
        // 각 원소가 안정화되기까지 걸리는 연산 횟수를 구한 뒤, 그 중 최댓값 반환
        return Arrays.stream(arr)
                     .map(this::cntAction)
                     .max()
                     .getAsInt();
    }

    // 특정 숫자 n이 더 이상 변하지 않을 때까지 몇 번의 연산이 필요한지 계산
    private int cntAction(int n) {
        int cnt = 0;

        while (true) {
            // 조건 1: 짝수이면서 50 이상인 경우 → 2로 나눔
            if (n % 2 == 0 && n >= 50) {
                n /= 2;
                cnt++;
            }
            // 조건 2: 홀수이면서 50 미만인 경우 → 2배 후 1을 더함
            else if (n % 2 == 1 && n < 50) {
                n = n * 2 + 1;
                cnt++;
            }
            // 위 두 조건에 모두 해당하지 않으면 안정화된 상태 → 종료
            else {
                break;
            }
        }

        return cnt;
    }
}
