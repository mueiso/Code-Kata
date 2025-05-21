class Solution {
    public String solution(String[] seoul) {
        
        // "Kim"의 위치 찾는 방법
        int x = -1; // "Kim"의 위치 저장할 변수
        
        // 배열 순회하면서 "Kim"의 인덱스 찾기
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                x = i;
                break; // "Kim" 찾으면 더 이상 반복할 필요 없다
            }
        }

        return "김서방은 " + x + "에 있다";
    }
}