class Solution {
    public long solution(int price, int money, int count) {
        // 등차수열의 합 공식 사용
        long totalCost = (long) price * count * (count + 1) / 2;
        
        // 금액이 부족하지 않다면 0 반환
        return Math.max(totalCost - money, 0);
    }
}