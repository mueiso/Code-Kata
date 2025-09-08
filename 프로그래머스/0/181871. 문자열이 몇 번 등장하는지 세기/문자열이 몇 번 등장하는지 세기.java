class Solution {
    
    public int solution(String myString, String pat) {
        
        int count = 0;        // pat이 등장한 횟수
        int idx = 0;          // 탐색 시작 위치

        while ((idx = myString.indexOf(pat, idx)) != -1) {
            count++;          // 발견 시 횟수 증가
            idx++;            // 다음 탐색은 겹쳐 있는 경우도 포함해야 하므로 한 칸 뒤에서 시작
        }

        return count;
    }
}
