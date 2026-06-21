class Solution {
    
    public int solution(int n) {
        
        // 1. 입력받은 n의 이진수 기준 1의 개수를 구합니다.
        int targetCount = Integer.bitCount(n);
        
        // 2. n보다 1 큰 수부터 시작합니다.
        int nextNum = n + 1;
        
        // 3. 조건 1, 2를 만족할 때까지 1씩 더하며 무한 루프를 돕니다.
        while (true) {
            if (Integer.bitCount(nextNum) == targetCount) {
                break; // 1의 개수가 같아지면 루프 탈출
            }
            nextNum++; // 다르면 다음 숫자로
        }
        
        return nextNum;
    }
}