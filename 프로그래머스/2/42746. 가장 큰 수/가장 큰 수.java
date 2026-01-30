import java.util.*;

class Solution {
    
    public String solution(int[] numbers) {
        
        // int 배열을 String 배열로 변환
        String[] arr = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        
        // (b+a) vs (a+b)를 비교해서 내림차순 정렬
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
        
        // 가장 큰 수가 0이면 전체가 0
        if (arr[0].equals("0")) {
            return "0";
        }
        
        // 정렬된 문자열 이어붙이기
        StringBuilder sb = new StringBuilder();
        
        for (String s : arr) {
            sb.append(s);
        }
        
        return sb.toString();
    }
}