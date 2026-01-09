import java.util.*;

class Solution {
    
    public int solution(int[] elements) {
        
        int n = elements.length;

        // 원형 처리를 위해 2배 길이로 확장한 prefix sum 만들기
        int[] prefix = new int[2 * n + 1];
        for (int i = 0; i < 2 * n; i++) {
            prefix[i + 1] = prefix[i] + elements[i % n];
        }

        // 연속 부분 수열 합을 중복 없이 저장
        Set<Integer> sums = new HashSet<>();

        // 부분 수열 길이 len = 1..n
        for (int len = 1; len <= n; len++) {
            // 시작 위치 start = 0..n-1 (원형이므로 시작점은 n개만 보면 됨)
            for (int start = 0; start < n; start++) {
                int end = start + len;
                int sum = prefix[end] - prefix[start]; // 구간합
                sums.add(sum);
            }
        }

        return sums.size();
    }
}