class Solution {
    
    public int solution(int[][] signals) {
        
        long totalLcm = 1;

        // 1. 모든 신호등 주기의 최소공배수(LCM) 구하기
        for (int[] signal : signals) {
            long period = signal[0] + signal[1] + signal[2];
            totalLcm = lcm(totalLcm, period);
        }

        // 2. 1초부터 LCM초까지 시뮬레이션
        for (int t = 1; t <= totalLcm; t++) {
            boolean allYellow = true;

            for (int[] signal : signals) {
                int g = signal[0];
                int y = signal[1];
                int period = g + y + signal[2];

                int rem = (t - 1) % period;
                // 노란불 범위: G <= rem < G + Y
                if (rem < g || rem >= g + y) {
                    allYellow = false;
                    break;
                }
            }

            if (allYellow) {
                return t;
            }
        }

        return -1;
    }

    private long gcd(long a, long b) {
        
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        
        return a;
    }

    private long lcm(long a, long b) {
        
        return (a / gcd(a, b)) * b;
    }
}