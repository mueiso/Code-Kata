class Solution {
    public int solution(int n, int k) {
        int service = n / 10;             // 10인분당 1개의 서비스 음료
        int realK = k - service;          // 실제로 계산할 음료수 개수
        int answer = (n * 12000) + (realK * 2000);
        return answer;
    }
}
