import java.util.Arrays;

class Solution {
    
    public int solution(int n, int[] lost, int[] reserve) {
        
        int[] uniform = new int[n + 1]; // 각 학생의 체육복 수 (1번~n번)
        Arrays.fill(uniform, 1);        // 기본 1벌씩 보유

        // 도난당한 학생은 -1
        for (int s : lost) uniform[s]--;

        // 여벌 가져온 학생은 +1
        for (int s : reserve) uniform[s]++;

        // 체육복이 없는 학생에게 빌려주기
        for (int s = 1; s <= n; s++) {
            if (uniform[s] != 0) continue; // 이미 체육복 있으면 스킵

            // 왼쪽 학생이 여벌(2벌)이면 먼저 빌림
            if (s > 1 && uniform[s - 1] == 2) {
                uniform[s - 1]--;
                uniform[s]++;
            }
            // 아니면 오른쪽 학생에게 빌림
            else if (s < n && uniform[s + 1] == 2) {
                uniform[s + 1]--;
                uniform[s]++;
            }
        }

        // 체육수업 가능한 학생 수 세기
        int attendable = 0;
        for (int s = 1; s <= n; s++) {
            if (uniform[s] >= 1) attendable++;
        }
        return attendable;
    }
}