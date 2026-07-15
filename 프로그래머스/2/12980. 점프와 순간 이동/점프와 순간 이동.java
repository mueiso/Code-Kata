import java.util.*;

public class Solution {
    
    public int solution(int n) {
        
        int ans = 0;

        while (n > 0) {
            // 홀수라면 1을 빼고 건전지 1 소모
            if (n % 2 != 0) {
                ans++;
            }
            // 반으로 줄이기 (순간이동의 역과정)
            n /= 2;
        }

        return ans;
    }
}