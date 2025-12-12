class Solution {
    
    public String solution(String X, String Y) {
        
        int[] countX = new int[10];
        int[] countY = new int[10];

        // X에 등장하는 숫자 개수 세기
        for (char c : X.toCharArray()) {
            countX[c - '0']++;
        }

        // Y에 등장하는 숫자 개수 세기
        for (char c : Y.toCharArray()) {
            countY[c - '0']++;
        }

        StringBuilder sb = new StringBuilder();

        // 큰 수를 만들기 위해 9부터 0까지 역순으로 탐색
        for (int d = 9; d >= 0; d--) {
            int common = Math.min(countX[d], countY[d]); // 둘 다에서 짝지을 수 있는 개수
            for (int i = 0; i < common; i++) {
                sb.append(d);
            }
        }

        // 공통 숫자가 전혀 없는 경우
        if (sb.length() == 0) {
            return "-1";
        }

        // 0으로만 구성된 경우 (예: "0", "00"...)
        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }
}

/*
 1. X와 Y 각각에서 0~9 숫자가 몇 번씩 나오는지 센다.

 2. 각 숫자 d에 대해 min(cntX[d], cntY[d]) 만큼만 사용할 수 있다.

 3. 큰 수가 되려면 9부터 0까지 내려가면서 숫자를 붙이면 된다.

 4. 예외 처리:
    공통 숫자가 하나도 없으면 → "-1"
    만든 문자열이 "000..." 형태라면 → "0"
 */