class Solution {
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            // 1. 두 지도의 같은 행을 비트 OR 연산으로 합칩니다.
            int combined = arr1[i] | arr2[i];
            
            // 2. 이진수 문자열로 변환합니다. (예: "10101")
            String binary = Integer.toBinaryString(combined);
            
            // 3. n 자릿수를 맞추기 위해 앞에 공백을 채워줍니다.
            // %5s 형태로 포맷을 지정하면 모자란 길이만큼 앞에 공백이 생깁니다.
            String padded = String.format("%" + n + "s", binary);
            
            // 4. 규칙에 맞게 1은 '#', 0은 ' '으로 치환합니다.
            // 이미 3번에서 채워진 앞쪽 공백들은 '0'이 아니므로 replace('0', ' ')에 영향을 받지 않고 공백으로 유지됩니다.
            padded = padded.replace('1', '#').replace('0', ' ');
            
            answer[i] = padded;
        }
        
        return answer;
    }
}