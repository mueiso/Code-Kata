class Solution {
    public int solution(int chicken) {
        int service = 0;
        int coupon = chicken;

        while (coupon >= 10) {
            int extra = coupon / 10;     // 서비스 치킨 개수
            service += extra;
            coupon = extra + (coupon % 10);  // 남은 쿠폰
        }

        return service;
    }
}
