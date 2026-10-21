class Solution {
    
    public int solution(int[] cookie) {
        
        int n = cookie.length;
        int answer = 0;

        // m은 첫째 아들의 마지막 바구니 인덱스
        for (int m = 0; m < n - 1; m++) {
            int l = m;
            int r = m + 1;
            int leftSum = cookie[l];
            int rightSum = cookie[r];

            while (true) {
                // 두 아들의 과자 수가 같으면 최댓값 갱신
                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }

                // leftSum이 작거나 같고 왼쪽으로 더 확장할 수 있다면 l을 왼쪽으로 이동
                if (leftSum <= rightSum && l > 0) {
                    l--;
                    leftSum += cookie[l];
                } 
                // rightSum이 작거나 같고 오른쪽으로 더 확장할 수 있다면 r을 오른쪽으로 이동
                else if (rightSum <= leftSum && r < n - 1) {
                    r++;
                    rightSum += cookie[r];
                } 
                else {
                    break;
                }
            }
        }

        return answer;
    }
}