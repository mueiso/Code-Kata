class Solution {
    
    public int[] solution(int[] sequence, int k) {
        
        int left = 0;
        int sum = 0;

        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;

        for (int right = 0; right < sequence.length; right++) {
            sum += sequence[right];

            // 합이 k보다 크면 왼쪽 포인터 이동
            while (sum > k) {
                sum -= sequence[left++];
            }

            // 합이 k인 구간 발견
            if (sum == k) {
                int len = right - left;

                // 더 짧은 구간이거나, 같은 길이지만 앞쪽인 경우
                if (len < minLen) {
                    minLen = len;
                    start = left;
                    end = right;
                }
            }
        }

        return new int[]{start, end};
    }
}