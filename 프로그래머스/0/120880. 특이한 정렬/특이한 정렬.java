import java.util.Arrays;

class Solution {
    public int[] solution(int[] numList, int n) {
        return Arrays.stream(numList)
                .boxed()
                .sorted((a, b) -> Math.abs(a - n) == Math.abs(b - n) ? b.compareTo(a) : Integer.compare(Math.abs(a - n), Math.abs(b - n)))
                .mapToInt(Integer::intValue)
                .toArray();
    }
}