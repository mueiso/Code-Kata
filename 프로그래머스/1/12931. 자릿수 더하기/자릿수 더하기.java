import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;  // 자릿수 합을 저장할 변수
        
        // n이 0보다 클 때마다 반복
        for (; n > 0; n = n / 10) {
            answer += n % 10;  // 마지막 자릿수를 answer에 더함
        }
        
        return answer;  // 자릿수 합 반환
    }
}
