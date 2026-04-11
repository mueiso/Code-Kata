class Solution {
    
    public int[] solution(int[][] edges) {
        
        // 1. 가장 큰 정점 번호를 찾아 배열 크기 지정
        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        // 2. 각 정점의 진입차수(in-degree)와 진출차수(out-degree) 배열 초기화
        int[] inDegree = new int[maxNode + 1];
        int[] outDegree = new int[maxNode + 1];

        // 3. 간선 정보를 바탕으로 차수 계산
        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }

        int createdNode = -1;
        int stickCount = 0;
        int figure8Count = 0;
        int donutCount = 0;

        // 4. 각 정점의 차수를 확인하여 그래프 종류 판별
        for (int i = 1; i <= maxNode; i++) {
            // [생성된 정점] 진입차수 0, 진출차수 2 이상
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                createdNode = i;
            } 
            // [막대 모양 그래프] 끝점은 진출차수 0
            else if (outDegree[i] == 0 && inDegree[i] > 0) {
                stickCount++;
            } 
            // [8자 모양 그래프] 중심점은 진입/진출차수 2 이상
            else if (outDegree[i] == 2 && inDegree[i] >= 2) {
                figure8Count++;
            }
        }

        // 5. 도넛 모양 그래프 수 계산 = 전체 그래프 수 - (막대 수 + 8자 수)
        // (전체 그래프 수 = 생성된 정점의 진출차수)
        donutCount = outDegree[createdNode] - stickCount - figure8Count;

        return new int[]{createdNode, donutCount, stickCount, figure8Count};
    }
}