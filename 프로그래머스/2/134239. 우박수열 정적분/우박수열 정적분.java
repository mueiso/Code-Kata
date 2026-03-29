/*
 * 매번 넓이 계산하면 느림 -> prefix sum으로 O(1) 처리
 
 * 1. 우박수열 생성
 * 2. 각 구간을 사다리꼴 넓이로 계산
      두 점 사이 넓이: (area) = (y[i] + y[i+1]) / 2 -> 이걸 prefix sum으로 누적 저장
 * 3. 쿼리 처리
      [a, b] → 실제 구간 [a, n + b]
      n = 전체 길이 -1 (만약 a > end 이 경우에는 -1)
      
 * 구간은 prefix[end] - prefix[start]
 */

import java.util.*;

class Solution {
    
    public double[] solution(int k, int[][] ranges) {

        // 1. 우박수열 생성 (k → 1까지)
        List<Integer> list = new ArrayList<>();
        list.add(k);

        while (k != 1) {
            if (k % 2 == 0) {
                k /= 2;          // 짝수 → 2로 나눔
            } else {
                k = k * 3 + 1;   // 홀수 → 3*k + 1
            }
            list.add(k);
        }

        // 전체 점 개수 (x좌표는 0 ~ n-1)
        int n = list.size();

        // 2. 각 구간의 넓이를 prefix sum으로 저장 → prefix[i] = 0 ~ i까지의 누적 넓이
        double[] prefix = new double[n];

        for (int i = 1; i < n; i++) {
            // (i-1, i) 구간은 사다리꼴
            double area = (list.get(i - 1) + list.get(i)) / 2.0;
            prefix[i] = prefix[i - 1] + area;
        }

        // 3. 각 range에 대해 정적분 계산
        double[] answer = new double[ranges.length];

        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];                 // 시작 x좌표
            int end = (n - 1) + ranges[i][1];         // 끝 x좌표 (b는 음수 가능)

            // 유효하지 않은 구간
            if (start > end) {
                answer[i] = -1.0;
                
                continue;
            }

            // prefix[end] - prefix[start] → [start, end] 구간 넓이
            answer[i] = prefix[end] - prefix[start];
        }

        return answer;
    }
}