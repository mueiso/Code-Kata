import java.util.*;

class Solution {
    
    public int solution(int n, int[] lost, int[] reserve) {
        
        // 1) 기본 상태: 모두 1벌
        int[] clothes = new int[n + 1];
        Arrays.fill(clothes, 1);

        // 2) 도난/여벌 반영
        for (int x : lost) clothes[x]--;
        for (int x : reserve) clothes[x]++;

        // 3) 빌려주기: 앞번호 -> 뒷번호 순으로 처리(그리디)
        for (int i = 1; i <= n; i++) {
            if (clothes[i] == 0) {
                if (i - 1 >= 1 && clothes[i - 1] == 2) {
                    clothes[i - 1]--;
                    clothes[i]++;
                } else if (i + 1 <= n && clothes[i + 1] == 2) {
                    clothes[i + 1]--;
                    clothes[i]++;
                }
            }
        }

        // 4) 수업 가능 인원 카운트
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (clothes[i] >= 1) answer++;
        }
        return answer;
    }
}