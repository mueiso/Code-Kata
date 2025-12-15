import java.util.Arrays;

class Solution {
    
    public int solution(int n, int[] lost, int[] reserve) {
        
        int[] uniform = new int[n + 1]; // 1..n
        Arrays.fill(uniform, 1);

        for (int s : lost) uniform[s]--;
        for (int s : reserve) uniform[s]++;

        for (int s = 1; s <= n; s++) {
            if (uniform[s] != 0) continue;

            // 왼쪽 학생이 여벌이 있으면 먼저 빌림(그리디)
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

        int attendable = 0;
        for (int s = 1; s <= n; s++) {
            if (uniform[s] >= 1) attendable++;
        }
        return attendable;
    }
}