import java.math.BigInteger;

class Solution {
    
    public String solution(String a, String b) {
        
        // 문자열을 BigInteger로 변환
        BigInteger numA = new BigInteger(a);
        BigInteger numB = new BigInteger(b);

        // 두 수를 더하기
        BigInteger sum = numA.add(numB);

        // 결과를 문자열로 변환해 반환
        return sum.toString();
    }
}
