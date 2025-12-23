class Solution {
    
    public int[] solution(String[] wallpaper) {
        
        // 파일(#)이 있는 좌표들의 최소/최대 범위 초기화
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        // 바탕화면 전체 탐색
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {

                // 파일을 발견하면 경계 갱신
                if (wallpaper[i].charAt(j) == '#') {
                    minX = Math.min(minX, i); // 가장 위쪽
                    minY = Math.min(minY, j); // 가장 왼쪽
                    maxX = Math.max(maxX, i); // 가장 아래쪽
                    maxY = Math.max(maxY, j); // 가장 오른쪽
                }
            }
        }

        // 드래그는 끝점이 포함되지 않으므로 +1
        return new int[]{minX, minY, maxX + 1, maxY + 1};
    }
}