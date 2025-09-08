class Solution {
    
    public int solution(String myString, String pat) {
        
        int cnt = 0; // pat이 등장하는 횟수를 저장할 변수

        // 문자열 myString의 모든 위치를 순회
        for (int i = 0; i < myString.length(); i++) {

            /*
             * i번째 인덱스부터 끝까지 잘라낸 문자열이 pat으로 시작하는지 확인
             * 즉, myString[i..]가 pat으로 시작하면 pat이 발견된 것
             */
            if (myString.substring(i).startsWith(pat)) {
                cnt++; // 발견했으므로 카운트 증가
            }
        }

        // pat이 발견된 총 횟수를 반환
        return cnt;
    }
}
