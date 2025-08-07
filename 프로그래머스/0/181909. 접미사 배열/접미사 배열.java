import java.util.Arrays;

class Solution {
    
    public String[] solution(String my_string) {
        
        // 문자열 길이만큼 접미사 배열 생성
        String[] suffixes = new String[my_string.length()];

        // 각 인덱스부터 시작하는 접미사를 배열에 저장
        for (int i = 0; i < my_string.length(); i++) {
            suffixes[i] = my_string.substring(i);
        }

        // 접미사 배열을 사전순으로 정렬
        Arrays.sort(suffixes);

        return suffixes;
    }
}
