import java.util.HashSet;
import java.util.Set;

class Solution {
    
    public int solution(String dirs) {
        
        // 중복 경로를 방지하기 위한 Set (양방향 저장)
        Set<String> visitedPaths = new HashSet<>();
        
        // 캐릭터의 현재 위치 (초기값: 0, 0)
        int cx = 0;
        int cy = 0;
        
        for (int i = 0; i < dirs.length(); i++) {
            char command = dirs.charAt(i);
            int nx = cx;
            int ny = cy;
            
            // 명령어에 따른 다음 위치 계산
            if (command == 'U') ny++;
            else if (command == 'D') ny--;
            else if (command == 'R') nx++;
            else if (command == 'L') nx--;
            
            // 좌표평면 경계를 벗어나는 이동은 무시
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }
            
            // 정방향 경로 및 역방향 경로 문자열 생성
            String path1 = cx + "," + cy + "->" + nx + "," + ny;
            String path2 = nx + "," + ny + "->" + cx + "," + cy;
            
            // Set에 두 방향 모두 저장 (중복이면 자동으로 무시됨)
            visitedPaths.add(path1);
            visitedPaths.add(path2);
            
            // 캐릭터 위치 갱신
            cx = nx;
            cy = ny;
        }
        
        // 양방향으로 저장했으므로 2로 나누어 실제 처음 걸어본 길의 길이 반환
        return visitedPaths.size() / 2;
    }
}