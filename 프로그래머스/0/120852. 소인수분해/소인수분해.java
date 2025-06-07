import java.util.*;

class Solution {
    public int[] solution(int n) {
        Set<Integer> primeFactors = new TreeSet<>(); // 자동 정렬 + 중복 제거
        
        int num = n;
        for (int i = 2; i <= num; i++) {
            while (num % i == 0) {
                primeFactors.add(i);
                num /= i;
            }
        }
        
        // Set을 배열로 변환
        int[] answer = new int[primeFactors.size()];
        int idx = 0;
        for (int factor : primeFactors) {
            answer[idx++] = factor;
        }
        
        return answer;
    }
}
