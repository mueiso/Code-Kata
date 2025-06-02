import java.util.Arrays;

class Solution {
    public int solution(int[] sides) {
        Arrays.sort(sides);  // 오름차순 정렬
        // 가장 긴 변이 sides[2], 나머지 두 변이 sides[0], sides[1]
        if (sides[2] < sides[0] + sides[1]) {
            return 1;  // 삼각형 가능
        } else {
            return 2;  // 삼각형 불가능
        }
    }
}
