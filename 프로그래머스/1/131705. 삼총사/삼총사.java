class Solution {
    public int solution(int[] number) {
        int count = 0;
        int 총길이 = number.length; // 배열 길이를 미리 저장해 반복문 내 계산을 줄임

        // 세 학생을 뽑는 모든 조합을 확인 (입력 크기 제한이 작을 경우 허용)
        for (int i = 0; i < 총길이; i++) {
            for (int j = i + 1; j < 총길이; j++) {
                for (int k = j + 1; k < 총길이; k++) {
                    int sum = number[i] + number[j] + number[k];
                    if (sum == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
} 
