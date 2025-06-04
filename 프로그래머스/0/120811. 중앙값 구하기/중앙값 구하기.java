import java.util.Arrays;

class Solution {
    public int solution(int[] array) {
        Arrays.sort(array); // 배열을 오름차순으로 정렬
        return array[array.length / 2]; // 가운데 인덱스의 값 반환
    }
}
