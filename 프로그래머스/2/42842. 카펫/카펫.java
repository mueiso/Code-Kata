class Solution {
    
    public int[] solution(int brown, int yellow) {
        
        // 전체 격자 수 = 가로*세로
        int total = brown + yellow;

        // yellow의 약수 쌍을 찾는다: (w-2) * (h-2) = yellow
        for (int h = 1; h * h <= yellow; h++) {
            if (yellow % h != 0) continue;

            int w = yellow / h; // w >= h (약수 쌍)

            int W = w + 2; // 전체 가로
            int H = h + 2; // 전체 세로

            // 전체 면적이 total이면 정답
            if (W * H == total) {
                return new int[]{W, H}; // [가로, 세로]
            }
        }

        return new int[0]; // 문제 조건상 여기 도달하지 않음
    }
}