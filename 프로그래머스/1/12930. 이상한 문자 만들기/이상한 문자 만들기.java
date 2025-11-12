class Solution {
    
  public String solution(String s) {
      
        // 문자열을 문자 배열로 변환
        char[] chars = s.toCharArray();
        int idx = 0; // 각 단어 내에서의 문자 인덱스 (0부터 시작)

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ')
                // 공백을 만나면 새로운 단어 시작 → 인덱스 리셋
                idx = 0;
            else
                // 단어 내에서의 짝수/홀수 인덱스에 따라 대문자/소문자 변환
                chars[i] = (idx++ % 2 == 0)
                           ? Character.toUpperCase(chars[i]) // 짝수번째 → 대문자
                           : Character.toLowerCase(chars[i]); // 홀수번째 → 소문자
        }

        // 문자 배열을 다시 문자열로 변환하여 반환
        return String.valueOf(chars);
  }
}