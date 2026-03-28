class Solution {
    
    public int solution(int[] arrayA, int[] arrayB) {

        int gcdA = getGCD(arrayA);
        int gcdB = getGCD(arrayB);

        int resultA = check(gcdA, arrayB); // A 기준
        int resultB = check(gcdB, arrayA); // B 기준

        return Math.max(resultA, resultB);
    }

    // 배열의 GCD 구하기
    private int getGCD(int[] arr) {
        
        int gcd = arr[0];
        for (int i = 1; i < arr.length; i++) {
            gcd = gcd(gcd, arr[i]);
        }
        
        return gcd;
    }

    // gcd가 다른 배열을 나누는지 검사
    private int check(int gcd, int[] other) {
        
        for (int num : other) {
            
            if (num % gcd == 0) {
                return 0; // 하나라도 나눠지면 실패
            }
        }
        
        return gcd;
    }

    // 유클리드 호제법
    private int gcd(int a, int b) {
        
        if (b == 0) return a;
        
        return gcd(b, a % b);
    }
}