class Solution {
    
    public int solution(int[] a) {
        
        int n = a.length;
        if (n < 2) return 0;

        // 1. 각 원소의 등장 빈도수 계산
        int[] count = new int[n];
        for (int num : a) {
            count[num]++;
        }

        int maxPairs = 0;

        // 2. 교집합 원소가 될 v 후보 탐색
        for (int v = 0; v < n; v++) {
            // 가지치기: v의 총 빈도수가 현재 최고 쌍 개수 이하라면 탐색 불필요
            if (count[v] <= maxPairs) continue;

            int pairs = 0;
            int i = 0;

            // 3. 탐색 시작
            while (i < n - 1) {
                if ((a[i] == v || a[i + 1] == v) && a[i] != a[i + 1]) {
                    pairs++;
                    i += 2; // 쌍을 완성했으므로 2칸 이동
                } else {
                    i += 1; // 조건 불충족 시 1칸 이동
                }
            }

            maxPairs = Math.max(maxPairs, pairs);
        }

        // 스타 수열의 총 길이는 (쌍의 개수 * 2)
        return maxPairs * 2;
    }
}