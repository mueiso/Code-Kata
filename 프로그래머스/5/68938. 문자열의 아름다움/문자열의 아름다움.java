class Solution {
    
    public long solution(String s) {
        
        int n = s.length();
        long answer = c3(n + 1);                 // 모든 부분 문자열의 (len-1) 합

        int[][] cnt = new int[26][];
        int[] maxLen = new int[26];

        // 런 길이 계산을 위해 먼저 글자별 최대 런 길이를 구함
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            int c = s.charAt(i) - 'a';
            maxLen[c] = Math.max(maxLen[c], j - i);
            i = j;
        }
        for (int c = 0; c < 26; c++) cnt[c] = new int[maxLen[c] + 1];

        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            int a = j - i;
            answer -= c3(a + 1);                 // 한 런 안의 부분 문자열(아름다움 0)
            cnt[s.charAt(i) - 'a'][a]++;
            i = j;
        }

        // 같은 글자의 서로 다른 두 런에 걸친 부분 문자열의 min(L, R) 합 빼기
        for (int c = 0; c < 26; c++) {
            long C0 = 0, C1 = 0, C2 = 0;
            for (int x = maxLen[c]; x >= 1; x--) {
                long k = cnt[c][x];
                C0 += k;
                C1 += k * x;
                C2 += k * x * x;
                long y = x - 1;
                long S1 = C1 - y * C0;                       // Σ (a - x + 1)
                long S2 = C2 - 2 * y * C1 + y * y * C0;      // Σ (a - x + 1)^2
                answer -= (S1 * S1 - S2) / 2;
            }
        }
        
        return answer;
    }

    private long c3(long x) {                    // C(x, 3)
        
        if (x < 3) return 0;
        
        return x * (x - 1) * (x - 2) / 6;
    }
}