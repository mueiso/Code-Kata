import java.util.*;

class Solution {
    
    public int solution(int n, int[][] data) {
        
        int answer = 0;

        // 1. x, y 좌표 추출 및 중복 제거 후 정렬 (좌표 압축 준비)
        int[] xCoords = new int[n];
        int[] yCoords = new int[n];
        for (int i = 0; i < n; i++) {
            xCoords[i] = data[i][0];
            yCoords[i] = data[i][1];
        }

        Arrays.sort(xCoords);
        Arrays.sort(yCoords);

        int uX = 0, uY = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || xCoords[i] != xCoords[i - 1]) {
                xCoords[uX++] = xCoords[i];
            }
            if (i == 0 || yCoords[i] != yCoords[i - 1]) {
                yCoords[uY++] = yCoords[i];
            }
        }

        // 2. 좌표 압축 적용 (Binary Search 활용)
        int[][] compressed = new int[n][2];
        int[][] grid = new int[uX][uY];

        for (int i = 0; i < n; i++) {
            int cx = Arrays.binarySearch(xCoords, 0, uX, data[i][0]);
            int cy = Arrays.binarySearch(yCoords, 0, uY, data[i][1]);
            compressed[i][0] = cx;
            compressed[i][1] = cy;
            grid[cx][cy] = 1; // 쐐기 위치 표시
        }

        // 3. 2차원 누적 합 계산 (1-based indexing 사용)
        int[][] S = new int[uX + 1][uY + 1];
        for (int i = 0; i < uX; i++) {
            for (int j = 0; j < uY; j++) {
                S[i + 1][j + 1] = S[i][j + 1] + S[i + 1][j] - S[i][j] + grid[i][j];
            }
        }

        // 4. 모든 쐐기 쌍 (i, j) 검사
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = compressed[i][0];
                int y1 = compressed[i][1];
                int x2 = compressed[j][0];
                int y2 = compressed[j][1];

                // 직사각형 넓이가 0인 경우 제외 (동일 x 또는 y 좌표)
                if (x1 == x2 || y1 == y2) {
                    continue;
                }

                // 직사각형 내부 영역 (경계 제외)
                int minX = Math.min(x1, x2) + 1;
                int maxX = Math.max(x1, x2) - 1;
                int minY = Math.min(y1, y2) + 1;
                int maxY = Math.max(y1, y2) - 1;

                int cnt = 0;
                // 내부 영역이 존재하는 경우만 누적 합 조회
                if (minX <= maxX && minY <= maxY) {
                    cnt = S[maxX + 1][maxY + 1] - S[minX][maxY + 1] - S[maxX + 1][minY] + S[minX][minY];
                }

                // 내부에 쐐기가 하나도 없으면 조건 만족
                if (cnt == 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}