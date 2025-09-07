class Solution {
    
    public String solution(String myString, String pat) {
        
        // pat이 마지막으로 등장하는 인덱스를 찾음
        int idx = myString.lastIndexOf(pat);

        // 시작부터 idx + pat 길이까지 잘라내면 pat으로 끝나는 가장 긴 부분 문자열이 됨
        return myString.substring(0, idx + pat.length());
    }
}
