/*
 * currentSum: 첫 번째 요소부터 현재 요소까지 [1, -1, 1, ...] 펄스를 곱하며 더한 누적 합이다.
 * maxPrefix & minPrefix: 누적 합의 그래프가 그려진다고 상상했을 때, 가장 높이 올라간 고점(maxPrefix)과 가장 깊이 내려간 저점(minPrefix)이다.
 * maxPrefix - minPrefix: 그래프의 가장 높은 곳과 낮은 곳의 낙차가 곧 우리가 얻을 수 있는 가장 큰 부분 수열의 합이 된다. 만약 음수 펄스가 정답이라면 그래프가 반대로 뒤집힐 뿐이므로 낙차는 동일하게 적용된다.
 */

class Solution {
    
    public long solution(int[] sequence) {
        
        // 누적 합의 최댓값과 최솟값을 저장할 변수
        // 초기 누적 합 상태인 0을 반드시 포함해야 함
        long maxPrefix = 0; 
        long minPrefix = 0; 
        long currentSum = 0;
        
        for (int i = 0; i < sequence.length; i++) {
            
            // [1, -1, 1, -1 ...] 펄스를 곱하여 누적 합 계산
            // 짝수 인덱스는 1, 홀수 인덱스는 -1을 곱함
            long pulse = (i % 2 == 0) ? 1 : -1;
            currentSum += sequence[i] * pulse;
            
            // 누적 합의 최댓값, 최솟값 갱신
            maxPrefix = Math.max(maxPrefix, currentSum);
            minPrefix = Math.min(minPrefix, currentSum);
        }
        
        // 두 누적합의 최대 차이가 곧 만들 수 있는 연속 펄스 부분 수열의 최대 합
        return maxPrefix - minPrefix;
    }
}