class Solution {
    
    public int solution(int[] arr) {
        
        int x = 0; // 몇 번 반복했는지 기록하는 변수

        while (true) {
            
            // 현재 배열을 복사해둠 (이전 상태 비교용)
            int[] prev = arr.clone();

            // 규칙에 맞게 arr 변환
            for (int i = 0; i < arr.length; i++) {
                
                if (arr[i] >= 50 && arr[i] % 2 == 0) {
                    arr[i] /= 2;  // 50 이상 & 짝수 → 2로 나눔
                } else if (arr[i] < 50 && arr[i] % 2 == 1) {
                    arr[i] = arr[i] * 2 + 1; // 50 미만 & 홀수 → 2배 + 1
                }
            }

            // 변화가 없으면(arr(x) == arr(x+1)) 종료
            if (isSame(prev, arr)) {
                break;
            }

            x++; // 아직 변화가 있으면 횟수 증가 후 반복
        }

        return x;
    }

    // 두 배열이 같은지 비교하는 메서드
    private boolean isSame(int[] a, int[] b) {
        
        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }

        return true;
    }
}
