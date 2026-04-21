class Solution {
    
    public int solution(int[][] beginning, int[][] target) {
        
        int r = beginning.length;
        int c = beginning[0].length;
        
        // 1. 차이점(diff) 행렬 생성
        // 값이 다르면 1, 같으면 0
        int[][] diff = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                diff[i][j] = beginning[i][j] ^ target[i][j];
            }
        }
        
        int minFlips = Integer.MAX_VALUE;
        
        // 2. 첫 번째 행을 뒤집지 않는 경우(0)와 뒤집는 경우(1) 탐색
        for (int row0Flip = 0; row0Flip <= 1; row0Flip++) {
            int flips = 0;
            int[] colFlip = new int[c];
            
            // 첫 번째 행의 결정에 따라 모든 열(Column)의 뒤집기 여부 확정
            for (int j = 0; j < c; j++) {
                colFlip[j] = diff[0][j] ^ row0Flip;
                flips += colFlip[j]; // 열을 뒤집는 횟수 누적
            }
            flips += row0Flip; // 첫 번째 행을 뒤집었다면 횟수 누적
            
            boolean possible = true;
            
            // 3. 확정된 열 뒤집기 상태를 바탕으로 나머지 행들 검증
            for (int i = 1; i < r; i++) {
                // 해당 행을 뒤집을지 여부는 첫 번째 열(0번 인덱스)의 상태로 결정
                int rowFlip = diff[i][0] ^ colFlip[0];
                flips += rowFlip; // 행을 뒤집는 횟수 누적
                
                // 해당 행의 나머지 열들이 모순 없이 모두 0으로 변환되는지 확인
                for (int j = 1; j < c; j++) {
                    if ((diff[i][j] ^ rowFlip ^ colFlip[j]) != 0) {
                        possible = false;
                        break;
                    }
                }
                
                // 모순이 발견되면 더 이상 이 케이스는 탐색할 필요 없음
                if (!possible) break;
            }
            
            // 4. 모순 없이 목표 상태 도달이 가능하다면 최솟값 갱신
            if (possible) {
                minFlips = Math.min(minFlips, flips);
            }
        }
        
        // 두 케이스 모두 불가능했다면 -1 반환
        return minFlips == Integer.MAX_VALUE ? -1 : minFlips;
    }
} 