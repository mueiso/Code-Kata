/*
 * 문제 핵심: 앞에서 pop → 뒤에 insert → 큐 2개의 합을 같게 만드는 최소 횟수 구하기
 * 큐의 순서는 절대 바뀌지 않음
 * 즉, queue1 + queue2 를 원형처럼 도는 구조 → 연결된 배열에서 합이 target이 되는 연속 구간 찾기
 
 * queue1, queue2의 합 계산
 * 전체 합이 홀수면 불가능이므로 -1 반환
 * 두 큐를 하나의 배열 arr로 연결
 * 포인터 left, right로 현재 queue1의 범위를 표현
 * sum1을 target에 맞춰가며 이동 횟수 최소화
 */

class Solution {
    
    public int solution(int[] queue1, int[] queue2) {
        
        int n = queue1.length;

        long sum1 = 0, sum2 = 0;
        // 각 큐의 합 계산 (long 사용)
        for (int x : queue1) sum1 += x;
        for (int x : queue2) sum2 += x;

        long total = sum1 + sum2;
        // 전체 합이 홀수면 동일 분배 불가능
        if (total % 2 != 0) return -1;

        long target = total / 2;

        // 두 큐를 하나의 배열로 이어서 관리
        int[] arr = new int[n * 2];
        for (int i = 0; i < n; i++) {
            arr[i] = queue1[i];
            arr[i + n] = queue2[i];
        }

        int left = 0;   // queue1에서 빠질 위치
        int right = n;  // queue2에서 빠질 위치
        int count = 0;

        // 포인터가 배열 범위를 넘지 않도록 제한
        while (left < n * 2 && right < n * 2) {
            // 목표 합 도달 시 종료
            if (sum1 == target) {
                return count;
            }

            if (sum1 > target) {
                // queue1 → queue2 이동
                sum1 -= arr[left++];
            } else {
                // queue2 → queue1 이동
                sum1 += arr[right++];
            }

            count++;
        }

        // 끝까지 못 맞추면 불가능
        return -1;
    }
}