class Solution {
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int t = bandage[0];   // 연속 성공 시간
        int x = bandage[1];   // 초당 회복량
        int y = bandage[2];   // 추가 회복량
        
        int currentHealth = health;  // 현재 체력
        int combo = 0;               // 연속 성공 시간
        int prevTime = 0;            // 이전 공격 시간
        
        for (int[] attack : attacks) {
            
            int attackTime = attack[0];
            int damage = attack[1];
            
            // 1. 이전 공격 이후 ~ 이번 공격 직전까지 회복
            int timeDiff = attackTime - prevTime - 1;
            
            for (int i = 0; i < timeDiff; i++) {
                combo++;
                currentHealth += x;
                
                if (combo == t) {
                    currentHealth += y;
                    combo = 0;
                }
                
                // 최대 체력 초과 방지
                if (currentHealth > health) {
                    currentHealth = health;
                }
            }
            
            // 2. 공격 당함 (이 순간 회복 없음)
            currentHealth -= damage;
            
            if (currentHealth <= 0) {
                return -1;
            }
            
            // 3. 공격 당했으므로 연속 시간 초기화
            combo = 0;
            prevTime = attackTime;
        }
        
        return currentHealth;
    }
}