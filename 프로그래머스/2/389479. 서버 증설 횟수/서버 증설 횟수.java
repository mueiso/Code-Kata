/*
 * active 관리: "전체 필요한 서버"가 아니라 "현재 가동 중인 증설 서버"를 관리하는 것이 핵심이다. 
   문제에서 기본적으로 제공되는 서버에 대한 언급은 없으므로, m명당 추가되는 서버 대수만 신경 쓰면 된다.
 * install[i - k]: 이 코드가 이 알고리즘의 '킥'이다. 큐(Queue)를 써서 만료 시간을 관리할 수도 있지만, 
   시간대가 24개로 고정되어 있으므로 배열을 쓰는 것이 훨씬 효율적이고 직관적이다.
 * 정수 나눗셈 (/): players[i] / m은 players[i]가 m보다 작으면 0, m 이상 2m 미만이면 1을 반환하므로 
   문제의 조건을 완벽하게 만족한다.
 */

class Solution {
    
    public int solution(int[] players, int m, int k) {
        
        // 총 증설 횟수
        int ans = 0;
        
        // 현재 가동 중인 증설 서버의 총 대수
        int active = 0;
        
        // 각 시간대별 신규 증설 서버 수를 기록 (반납 추적용)
        int[] install = new int[24]; 

        // 0시부터 23시까지 순회
        for (int i = 0; i < 24; i++) {
            
            // [서버 반납] 현재 시간이 k 이상이면 k시간 전(i-k)에 증설한 서버는 만료됨
            if (i >= k) {
                active -= install[i - k]; // 가동 서버 수에서 제외
            }
            
            // [필요 서버 계산] n*m명 기준에 맞춰 필요한 증설 서버 대수 계산
            int req = players[i] / m;
            
            // [서버 증설] 현재 가동 중인 서버가 필요한 양보다 적으면 추가 증설
            if (active < req) {
                int add = req - active; // 부족한 만큼의 대수
                
                ans += add;         // 전체 증설 횟수 누적
                active += add;      // 현재 가동 서버 수 증가
                install[i] = add;   // 해당 시각의 증설 기록 저장
            }
        }
        
        return ans;
    }
}