/*
 * 시간(time)을 키로 갖는 맵 안에 다시 좌표(r, c)를 키로 갖는 맵을 두어 관리한다.
   -> 특정 시간에 어떤 위치에 로봇이 몇 대 있었는지 아주 빠르게 조회할 수 있다.
 * while문을 두 개 사용하여 currentPose[0] (r좌표)가 목표에 도달할 때까지 먼저 증감시키고,
   그 다음 currentPose[1] (c좌표)를 처리하여 'r 우선' 조건을 충족한다.
 * 로봇이 첫 번째 포인트에 서 있는 0초 시점도 위험 상황 체크에 포함되어야 하므로 루프 시작 지점에 기록을 먼저 해준다.
 */

import java.util.*;

class Solution {
    
    public int solution(int[][] points, int[][] routes) {
        
        // 시간대별로 특정 좌표(String)에 로봇이 몇 대 있는지 저장할 맵
        // Map<시간, Map<좌표문자열, 로봇수>>
        Map<Integer, Map<String, Integer>> timeMap = new HashMap<>();
        int maxTime = 0;

        for (int i = 0; i < routes.length; i++) {
            
            int time = 0;
            // 1. 로봇의 첫 번째 포인트 위치 기록
            int[] currentPos = points[routes[i][0] - 1].clone();
            recordPosition(timeMap, time, currentPos[0], currentPos[1]);

            // 2. 이후 경유지들을 순서대로 방문
            for (int j = 1; j < routes[i].length; j++) {
                int[] targetPos = points[routes[i][j] - 1];

                // r 좌표 이동 (r 우선 규칙)
                while (currentPos[0] != targetPos[0]) {
                    
                    if (currentPos[0] < targetPos[0]) currentPos[0]++;
                    else currentPos[0]--;
                    time++;
                    recordPosition(timeMap, time, currentPos[0], currentPos[1]);
                }

                // c 좌표 이동
                while (currentPos[1] != targetPos[1]) {
                    
                    if (currentPos[1] < targetPos[1]) currentPos[1]++;
                    else currentPos[1]--;
                    time++;
                    recordPosition(timeMap, time, currentPos[0], currentPos[1]);
                }
            }
            maxTime = Math.max(maxTime, time);
        }

        // 3. 위험 상황(충돌 가능성) 계산
        int answer = 0;
        for (int t = 0; t <= maxTime; t++) {
            
            Map<String, Integer> positions = timeMap.get(t);
            if (positions == null) continue;
            
            for (int count : positions.values()) {
                
                if (count >= 2) {
                    answer++;
                }
            }
        }

        return answer;
    }

    /* 특정 시간에 특정 좌표에 로봇이 있음을 기록하는 헬퍼 함수 */
    private void recordPosition(Map<Integer, Map<String, Integer>> timeMap, int time, int r, int c) {
        
        timeMap.putIfAbsent(time, new HashMap<>());
        Map<String, Integer> posCount = timeMap.get(time);
        String key = r + "," + c;
        posCount.put(key, posCount.getOrDefault(key, 0) + 1);
    }
}