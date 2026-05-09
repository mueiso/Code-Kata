class Solution {
    
    public int solution(int[] a) {
        
        int n = a.length;
        
        // 풍선이 1개나 2개인 경우는 무조건 모두 끝까지 남길 수 있습니다.
        if (n <= 2) return n;

        // 왼쪽과 오른쪽에서의 최솟값을 저장할 배열
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];

        // 1. 각 인덱스 기준으로 왼쪽 방향의 최솟값 채우기
        leftMin[0] = a[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i]);
        }

        // 2. 각 인덱스 기준으로 오른쪽 방향의 최솟값 채우기
        rightMin[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }

        int answer = 0;
        
        // 3. 조건 판별: 양쪽 최솟값 중 하나라도 자신보다 크거나 같다면 살아남을 수 있음
        for (int i = 0; i < n; i++) {
            if (a[i] <= leftMin[i] || a[i] <= rightMin[i]) {
                answer++;
            }
        }

        return answer;
    }
}