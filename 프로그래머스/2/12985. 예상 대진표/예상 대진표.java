class Solution {
    
    public int solution(int n, int a, int b) {
        
        int round = 0;

        // A와 B가 같은 번호가 될 때까지(=같은 대진에 들어갈 때까지) 반복
        while (a != b) {
            a = (a + 1) / 2;  // 다음 라운드에서의 A의 번호
            b = (b + 1) / 2;  // 다음 라운드에서의 B의 번호
            round++;          // 라운드 진행
        }

        return round;
    }
}