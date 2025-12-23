class Solution {
    
    public int[] solution(String[] wallpaper) {
        
        int lux = Integer.MAX_VALUE;
        int luy = Integer.MAX_VALUE;
        int rdx = Integer.MIN_VALUE;
        int rdy = Integer.MIN_VALUE;

        for (int i = 0; i < wallpaper.length; i++) {
            String row = wallpaper[i];
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '#') {
                    lux = Math.min(lux, i);     // 파일이 있는 최소 행
                    luy = Math.min(luy, j);     // 파일이 있는 최소 열
                    rdx = Math.max(rdx, i + 1); // 격자점 기준이라 +1
                    rdy = Math.max(rdy, j + 1); // 격자점 기준이라 +1
                }
            }
        }

        return new int[] {lux, luy, rdx, rdy};
    }
}