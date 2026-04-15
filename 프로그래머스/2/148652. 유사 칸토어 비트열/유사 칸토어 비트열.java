class Solution {
    
    public int solution(int n, long l, long r) {
        
        // [l, r] 구간의 1의 개수 = (1 ~ r까지의 1의 개수) - (1 ~ l-1까지의 1의 개수)
        long answer = countOnes(n, r) - countOnes(n, l - 1);
        
        return (int) answer;
    }

    /* n번째 비트열에서 1번 인덱스부터 k번 인덱스까지의 1의 개수를 구하는 재귀 함수 */
    private long countOnes(int n, long k) {
        
        // [Base Case 1] n이 0일 때는 "1" 하나뿐이므로 k가 1이면 1, 아니면 0 반환
        if (n == 0) {
            
            return k;
        }
        
        // [Base Case 2] k가 0이면 1의 개수도 0
        if (k == 0) {
            
            return 0;
        }

        // 이전 단계(n-1) 비트열 하나의 길이 (5^(n-1))
        long prevLen = (long) Math.pow(5, n - 1);
        // 이전 단계(n-1) 비트열 하나에 들어있는 1의 총 개수 (4^(n-1))
        long prevOnes = (long) Math.pow(4, n - 1);

        // k가 몇 번째 구역에 속하는지 (0부터 시작: 0, 1, 2, 3, 4 구역)
        long zone = k / prevLen;
        // 해당 구역에서 남은 나머지 위치
        long rem = k % prevLen;

        // 구역에 따라 1의 개수 합산
        if (zone == 0 || zone == 1) {
            
            // 1, 2번째 구역: 앞선 꽉 찬 구역 수(zone) * 이전 단계 1의 개수 + 나머지 재귀
            return zone * prevOnes + countOnes(n - 1, rem);
        } else if (zone == 2) {
            
            // 3번째 구역: 0으로만 이루어져 있으므로 나머지는 볼 필요 없이 앞선 2개 구역의 1의 개수만 반환
            return 2 * prevOnes;
        } else {
            
            // 4, 5번째 구역: 3번째 구역(00000)이 비어있으므로 (zone - 1)만큼 곱해주고 나머지 재귀
            return (zone - 1) * prevOnes + countOnes(n - 1, rem);
        }
    }
}