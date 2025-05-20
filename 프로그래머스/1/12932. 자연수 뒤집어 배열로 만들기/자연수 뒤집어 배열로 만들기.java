class Solution {
    public int[] solution(long n) {
        // 숫자를 문자열로 바꾸고, 뒤집어서 각 자리를 배열로 변환
        return new StringBuilder(Long.toString(n))
                    .reverse()
                    .toString()
                    .chars()
                    .map(c -> c - '0')
                    .toArray();
    }
}
