class Solution {
    
    public String solution(String s) {
        
        // 문자열의 중앙 인덱스 계산
        int mid = s.length() / 2;

        // 문자열 길이가 홀수면 가운데 한 글자,
        // 짝수면 가운데 두 글자를 반환
        return (s.length() % 2 == 0)
                ? s.substring(mid - 1, mid + 1)  // 짝수 → 가운데 두 글자
                : s.substring(mid, mid + 1);     // 홀수 → 가운데 한 글자
    }
}