class Solution {

    // 클래스 전역으로 사용할 문자 배열 선언
    char[] arr;

    public String solution(String my_string, int[][] queries) {

        // 입력 문자열을 문자 배열로 변환 (문자열은 불변이므로 배열로 바꾸면 수정이 쉬움)
        arr = my_string.toCharArray();

        // 주어진 쿼리 목록을 순차적으로 처리
        for (int[] query : queries) {
            // 각 쿼리는 [s, e] 형태로, s부터 e까지의 문자열을 뒤집는 작업
            reverse(query[0], query[1]);
        }

        // 수정된 문자 배열을 문자열로 다시 변환하여 반환
        return new String(arr);
    }

    // s부터 e까지의 구간을 뒤집는 메서드
    private void reverse(int s, int e) {
        // s가 e보다 작을 동안 두 포인터를 이용해 문자를 교환
        while (s < e) {
            // 두 문자의 위치를 교환
            char temp = arr[s];
            arr[s++] = arr[e];
            arr[e--] = temp;
        }
    }
}
