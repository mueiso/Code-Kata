import java.util.Arrays;

class Solution {
    public int solution(String before, String after) {
        // 문자열을 문자 배열로 변환
        char[] beforeArr = before.toCharArray();
        char[] afterArr = after.toCharArray();

        // 정렬
        Arrays.sort(beforeArr);
        Arrays.sort(afterArr);

        // 정렬된 배열이 같으면 1, 다르면 0
        return Arrays.equals(beforeArr, afterArr) ? 1 : 0;
    }
}
