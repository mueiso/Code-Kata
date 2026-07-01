class Solution {
    
    public int solution(int n, int[] cores) {
        
        // 1. 만약 작업의 개수가 코어의 개수보다 작거나 같다면
        // 시작과 동시에 n번째 코어가 n번째 작업을 가져가므로 바로 n을 리턴합니다.
        if (n <= cores.length) {
            return n;
        }

        int len = cores.length;
        int low = 1;
        
        // 탐색할 시간의 최댓값(high)을 설정합니다.
        // 가장 빠른 코어 하나로만 n개의 작업을 다 처리한다고 가정했을 때의 시간입니다.
        int minCoreTime = 10000;
        for (int core : cores) {
            minCoreTime = Math.min(minCoreTime, core);
        }
        int high = n * minCoreTime;

        int time = 0;

        // 2. 이분 탐색으로 n개 이상의 작업을 처리할 수 있는 최소 시간(time)을 찾습니다.
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = len; // t=0일 때 모든 코어가 기본적으로 작업을 1개씩 가짐

            for (int i = 0; i < len; i++) {
                count += mid / cores[i];
            }

            if (count >= n) {
                time = mid;      // n개 이상 처리 가능하므로 시간 저장 후, 더 짧은 시간 탐색
                high = mid - 1;
            } else {
                low = mid + 1;   // 작업 수가 부족하므로 시간 늘리기
            }
        }

        // 3. 'time - 1' 시간까지 완료된 작업의 총 개수를 구합니다.
        int countBefore = len;
        for (int i = 0; i < len; i++) {
            countBefore += (time - 1) / cores[i];
        }

        // 4. 결정된 'time'에 새 작업을 시작하는 코어들을 순서대로 보며 n번째 작업을 찾습니다.
        for (int i = 0; i < len; i++) {
            if (time % cores[i] == 0) { // 현재 시간에 작업이 딱 끝난 코어 (새 작업 진입 가능)
                countBefore++;
                if (countBefore == n) {
                    return i + 1; // 코어 번호는 1부터 시작하므로 인덱스 + 1
                }
            }
        }

        return 0;
    }
}