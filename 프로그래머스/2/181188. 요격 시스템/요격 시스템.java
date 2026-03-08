import java.util.Arrays;

class Solution {
    
    public int solution(int[][] targets) {
        
        /*
         * 미사일 구간의 끝점(e)을 기준으로 오름차순 정렬
         * 가장 빨리 끝나는 구간부터 처리하면 그 구간의 끝 근처에서 요격할 때 이후 구간도 함께 요격될 가능성이 가장 높기 때문
         */
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        // 마지막으로 요격 미사일을 발사한 기준 위치 (처음에는 어떤 미사일도 요격하지 않았으므로 가장 작은 값으로 초기화)
        int last = Integer.MIN_VALUE;

        for (int[] t : targets) {
            int s = t[0];
            int e = t[1];

            /*
             * 미사일 구간은 (start, end) 형태의 "개구간" (미사일 구간은 (start, end) 형태의 "개구간")
             * 현재 미사일의 시작점이 이전 요격 기준 위치보다 크거나 같다면 이전에 발사한 요격 미사일로는 이 구간을 요격할 수 없다는 의미
             */
            if (s >= last) {
                count++;  // // 새로운 요격 미사일을 발사해야 함
                last = e; // 이 구간의 끝점 근처에서 요격한다고 생각하고 기준을 end로 설정
            }
        }

        // 필요한 최소 요격 미사일 개수 반환
        return count;
    }
}