import java.util.Arrays;

class Solution {
    
    public int[] solution(String myString) {
        
        // "x"로 split 하되, -1을 줘서 끝의 빈 문자열도 보존
        return Arrays.stream(myString.split("x", -1))
                     .mapToInt(String::length) // 문자열 길이 계산
                     .toArray();
    }
}
