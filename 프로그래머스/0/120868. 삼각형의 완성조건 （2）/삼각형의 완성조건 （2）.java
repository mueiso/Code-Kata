class Solution {
    public int solution(int[] sides) {
        int a = sides[0];
        int b = sides[1];

        int min = Math.abs(a - b);
        int max = a + b;

        // x는 (min, max) 사이의 정수
        return max - min - 1;
    }
}
