class Solution {
    public int solution(int M, int N) {
        // 종이를 1x1 크기로 자르려면, 총 조각 수는 M*N개
        // 처음에 종이 1장 -> 조각 M*N장으로 만들려면 자르는 횟수는 (M*N - 1)번
        int answer = M * N - 1;
        return answer;
    }
}
