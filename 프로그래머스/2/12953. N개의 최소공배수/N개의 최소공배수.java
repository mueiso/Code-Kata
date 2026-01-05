class Solution {
    
    public int solution(int[] arr) {

        int lcm = arr[0]; // 첫 번째 수로 초기화

        // 배열의 다음 수들과 차례대로 최소공배수 계산
        for (int i = 1; i < arr.length; i++) {
            lcm = lcm(lcm, arr[i]);
        }

        return lcm;
    }

    // 두 수의 최소공배수
    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // 두 수의 최대공약수 (유클리드 호제법)
    private int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}