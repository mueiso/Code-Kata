import java.util.ArrayList;
import java.util.List;

class Solution {
    
    public int[] solution(int n, long k) {
        
        int[] answer = new int[n];
        
        // 1. 사용할 수 있는 숫자들을 리스트에 순서대로 담음 (1부터 n까지)
        List<Integer> list = new ArrayList<>();
        long factorial = 1;
        
        // n! 값을 미리 계산하면서 리스트 초기화
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial *= i;
        }

        // 0번째 인덱스 기준으로 맞추기 위해 k에서 1을 감소
        k--;

        int idx = 0;
        while (n > 0) {
            // 현재 자리수를 고정했을 때, 남은 자리를 나열하는 경우의 수 (n-1)!
            factorial /= n; 
            
            // 현재 자리에 들어갈 숫자의 리스트 인덱스 계산
            int memberIdx = (int) (k / factorial);
            
            // 정답에 추가하고 리스트에서 제거
            answer[idx++] = list.get(memberIdx);
            list.remove(memberIdx);
            
            // 다음 단계를 위해 k 값을 나머지로 갱신
            k %= factorial;
            n--;
        }

        return answer;
    }
}