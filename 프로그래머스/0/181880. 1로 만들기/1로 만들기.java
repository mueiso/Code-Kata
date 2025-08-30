class Solution {
    
    public int solution(int[] num_list) {
        
        int answer = 0;

        for (int n : num_list) {
            // 각 원소 n을 1로 만들 때까지 반복
            while (n > 1) {
                if (n % 2 == 0) {
                    // 짝수라면 2로 나눔
                    n /= 2;
                } else {
                    // 홀수라면 1 빼고 2로 나눔
                    n = (n - 1) / 2;
                }
                answer++; // 연산 횟수 증가
            }
        }

        return answer;
    }
}
