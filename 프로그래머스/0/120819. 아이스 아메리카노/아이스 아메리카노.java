class Solution {
    public int[] solution(int money) {
        int price = 5500;
        int cups = money / price;       // 마실 수 있는 잔 수
        int change = money % price;     // 남는 돈

        return new int[]{cups, change};
    }
}
