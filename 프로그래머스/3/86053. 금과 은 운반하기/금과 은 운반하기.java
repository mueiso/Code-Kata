class Solution {
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        
        long low = 0;
        // 최악의 경우: (a + b) * 2 * t_max = 2 * 10^9 * 2 * 10^5 = 4 * 10^14
        long high = 1000000000000000L; // 10^15
        long answer = high;

        while (low <= high) {
            long mid = (low + high) / 2;

            if (isPossible(mid, a, b, g, s, w, t)) {
                answer = mid;
                high = mid - 1; // 더 적은 시간 탐색
            } else {
                low = mid + 1; // 시간 부족, 더 큰 시간 탐색
            }
        }

        return answer;
    }

    private boolean isPossible(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        
        long totalGold = 0;
        long totalSilver = 0;
        long totalMinerals = 0;

        int n = g.length;

        for (int i = 0; i < n; i++) {
            // time 시간 동안 트럭 i가 이동할 수 있는 횟수
            long roundTripTime = (long) t[i] * 2;
            long trips = (time + t[i]) / roundTripTime;

            long maxCapacity = trips * w[i];

            // 도시 i에서 가져올 수 있는 최대 금, 은, 총 광물량 누적
            totalGold += Math.min((long) g[i], maxCapacity);
            totalSilver += Math.min((long) s[i], maxCapacity);
            totalMinerals += Math.min((long) g[i] + s[i], maxCapacity);
        }

        return totalGold >= a && totalSilver >= b && totalMinerals >= ((long) a + b);
    }
}