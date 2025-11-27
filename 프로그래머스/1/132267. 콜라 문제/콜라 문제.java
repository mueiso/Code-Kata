class Solution {
    
    public int solution(int a, int b, int n) {
        
        int total = 0;  // 지금까지 받은 콜라 병 수 누적

        // 빈 병이 a개 이상 있어야 콜라로 교환 가능
        while (n >= a) {

            // 이번에 교환해서 받는 새 콜라 개수
            // (n / a)번 교환 가능하고, 1번당 b병씩 받음
            int newCoke = (n / a) * b;

            // 받은 콜라 수를 총합에 더하기
            total += newCoke;

            // 남은 빈 병 개수 갱신:
            // 1) 교환 후 남은 빈 병: n % a
            // 2) 새로 받은 콜라를 다 마시면, 그만큼 빈 병이 생김: newCoke
            n = (n % a) + newCoke;
        }

        // 더 이상 교환 불가(a개 미만)면 종료
        return total;
    }
}