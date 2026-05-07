import java.util.*;

class Solution {

    public String[] solution(int[][] line) {

        // 정수 좌표 교점 저장
        List<long[]> points = new ArrayList<>();

        // 별들의 최소/최대 좌표 범위
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        // 모든 직선 쌍 비교
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {

                long A = line[i][0];
                long B = line[i][1];
                long C = line[i][2];

                long D = line[j][0];
                long E = line[j][1];
                long F = line[j][2];

                // 분모
                long denominator = A * E - B * D;

                // 평행 또는 동일 직선이면 교점 없음
                if (denominator == 0) {
                    continue;
                }

                // 교점 공식
                long xNumerator = B * F - C * E;
                long yNumerator = C * D - A * F;

                // 정수 좌표가 아니면 제외
                if (xNumerator % denominator != 0 ||
                    yNumerator % denominator != 0) {
                    continue;
                }

                // 정수 교점
                long x = xNumerator / denominator;
                long y = yNumerator / denominator;

                points.add(new long[]{x, y});

                // 좌표 범위 갱신
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
        }

        // 격자 크기 계산
        int width = (int)(maxX - minX + 1);
        int height = (int)(maxY - minY + 1);

        // 전체 '.'로 초기화
        char[][] board = new char[height][width];

        for (int i = 0; i < height; i++) {
            Arrays.fill(board[i], '.');
        }

        // 교점 위치에 '*' 표시
        for (long[] point : points) {

            long x = point[0];
            long y = point[1];

            // 좌표 -> 배열 인덱스 변환
            int row = (int)(maxY - y);
            int col = (int)(x - minX);

            board[row][col] = '*';
        }

        // char[][] -> String[] 변환
        String[] answer = new String[height];

        for (int i = 0; i < height; i++) {
            answer[i] = new String(board[i]);
        }

        return answer;
    }
}