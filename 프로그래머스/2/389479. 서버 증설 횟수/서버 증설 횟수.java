/*
 * adds 배열: 크기가 24인 배열을 생성하여 각 시각(0시~23시)에 "새롭게 추가된" 서버의 대수만 따로 기록한다.
   이를 통해 나중에 어떤 서버를 언제 반납해야 하는지 쉽게 알 수 있다.

 * i >= k 조건문: 예를 들어 k가 5일 때, 5시(i=5)가 되면 0시(i-k = 0)에 추가했던 서버는 수명이 다해 반납되어야 한다.
   따라서 adds[i - k] 만큼을 현재 가동 중인 서버 변수(runningActive)에서 차감 해준다.

 * newServers 계산: 이번 시간대에 필요한 총 서버(required)가 현재 켜져 있는 서버(runningActive)보다 많을 때만
   그 차이만큼 새롭게 서버를 켠다.
 */

class Solution {
    
    public int solution(int[] players, int m, int k) {
        
        int answer = 0;
        
        // 각 시간대(0~23)별로 새롭게 증설한 서버의 수를 저장할 배열
        int[] adds = new int[24]; 
        
        // 현재 가동 중인(운영 중인) 증설 서버의 총합
        int runningActive = 0; 
        
        for (int i = 0; i < 24; i++) {
            // 1. 유효기간(k)이 끝난 서버 반납 처리
            // 현재 시간 i에서 k시간 전에 증설한 서버는 수명이 다했으므로 제외합니다.
            if (i >= k) {
                runningActive -= adds[i - k];
            }
            
            // 2. 현재 시간대에 필요한 최소 증설 서버 수 계산
            int required = players[i] / m;
            
            // 3. 서버가 부족하다면 추가 증설
            if (required > runningActive) {
                int newServers = required - runningActive; // 부족한 만큼의 서버 수
                
                adds[i] = newServers;         // 이번 시간대에 증설한 서버 수 기록
                runningActive += newServers;  // 현재 가동 중인 서버 수에 누적
                answer += newServers;         // 총 증설 횟수 누적
            }
        }
        
        return answer;
    }
}