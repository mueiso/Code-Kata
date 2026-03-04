class Solution {
    
    boolean solution(String s) {

        int pCount = 0;
        int yCount = 0;

        // 문자열을 한 글자씩 확인
        for (char c : s.toCharArray()) {

            // 대소문자 구분 없이 비교하기 위해 소문자로 변환
            char lower = Character.toLowerCase(c);

            if (lower == 'p') {
                pCount++;
            } else if (lower == 'y') {
                yCount++;
            }
        }

        // p와 y의 개수가 같으면 true, 다르면 false
        return pCount == yCount;
    }
}