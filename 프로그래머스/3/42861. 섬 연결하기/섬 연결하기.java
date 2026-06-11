import java.util.Arrays;

class Solution {
    
    private int[] parent;

    public int solution(int n, int[][] costs) {
        
        int answer = 0;

        // 1. 다리 건설 비용(인덱스 2)을 기준으로 오름차순 정렬
        Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        // 2. 유니온 파인드를 위한 부모 배열 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int bridgeCount = 0; // 연결된 다리 개수

        // 3. 비용이 저렴한 다리부터 하나씩 확인
        for (int[] cost : costs) {
            // n - 1개의 다리가 놓였다면 모든 섬이 연결된 것이므로 종료
            if (bridgeCount == n - 1) {
                break;
            }

            int islandA = cost[0];
            int islandB = cost[1];
            int price = cost[2];

            // 두 섬의 최상위 부모가 다르다면 (사이클이 생기지 않는다면)
            if (find(islandA) != find(islandB)) {
                union(islandA, islandB); // 두 섬을 연결
                answer += price;         // 비용 누적
                bridgeCount++;           // 다리 개수 증가
            }
        }

        return answer;
    }

    /* 최상위 부모(대표 노드)를 찾는 함수 (경로 압축 적용) */
    private int find(int x) {
        
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }

    /* 두 집합을 하나로 합치는 함수 */
    private void union(int x, int y) {
        
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}