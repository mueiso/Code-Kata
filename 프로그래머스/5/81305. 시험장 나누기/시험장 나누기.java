import java.util.*;

class Solution {
    
    static int[] nodeNum;
    static int[][] nodeLinks;
    static int groups;

    public int solution(int k, int[] num, int[][] links) {
        
        nodeNum = num;
        nodeLinks = links;
        int n = num.length;

        int left = 0;
        int right = 0;
        for (int val : num) {
            left = Math.max(left, val);
            right += val;
        }

        // 부모 노드 찾기 (루트 판별용)
        boolean[] hasParent = new boolean[n];
        for (int[] link : links) {
            if (link[0] != -1) hasParent[link[0]] = true;
            if (link[1] != -1) hasParent[link[1]] = true;
        }

        int root = 0;
        for (int i = 0; i < n; i++) {
            if (!hasParent[i]) {
                root = i;
                break;
            }
        }

        int answer = right;

        // 파라메트릭 서치 (이분 탐색)
        while (left <= right) {
            int mid = (left + right) / 2;
            groups = 0;

            dfs(root, mid);

            // 필요한 그룹 수가 k 이하이면 limit을 줄여서 더 최솟값 탐색
            if (groups + 1 <= k) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    /* 후위 순회를 통해 limit 이하로 그룹을 나누며 필요한 간선 끊기 횟수(그룹 수)를 계산하는 DFS 메서드 */
    private int dfs(int curr, int limit) {
        
        if (curr == -1) return 0;

        int leftSum = dfs(nodeLinks[curr][0], limit);
        int rightSum = dfs(nodeLinks[curr][1], limit);

        // 1. 현재 노드와 왼쪽, 오른쪽 자식 모두 합칠 수 있는 경우
        if (nodeNum[curr] + leftSum + rightSum <= limit) {
            return nodeNum[curr] + leftSum + rightSum;
        }

        // 2. 둘 중 더 작은 자식과만 합칠 수 있는 경우 (큰 자식 쪽 간선 1개 절단)
        if (nodeNum[curr] + Math.min(leftSum, rightSum) <= limit) {
            groups++;
            return nodeNum[curr] + Math.min(leftSum, rightSum);
        }

        // 3. 둘 다 합칠 수 없는 경우 (양쪽 자식 모두 간선 절단)
        groups += 2;
        return nodeNum[curr];
    }
}