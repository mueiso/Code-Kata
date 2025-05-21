import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        // divisor로 나누어 떨어지는 값을 담을 ArrayList
        int[] answer = Arrays.stream(arr)
                                 .filter(x -> x % divisor == 0) // divisor로 나누어 떨어지는 값만 필터링
                                 .toArray(); // 배열로 변환
        
        // 나누어 떨어지는 값이 없다면 -1을 담은 배열을 반환
        if (answer.length == 0) {
            return new int[] {-1};
        }
        
        // 나누어 떨어지는 값을 오름차순으로 정렬하여 반환 (sort 메서드)
        Arrays.sort(answer);
        return answer;
    }
}