class Solution {
    
    // 최종 0과 1의 개수를 저장할 배열 (0인덱스: 0의 개수, 1인덱스: 1의 개수)
    private int[] answer = new int[2];

    public int[] solution(int[][] arr) {
        
        // 처음에는 전체 영역(size = arr.length)부터 탐색 시작
        divide(0, 0, arr.length, arr);
        
        return answer;
    }

    /* 영역을 나누는 재귀 함수 */
    private void divide(int x, int y, int size, int[][] arr) {
        
        // 1. 현재 영역이 압축 가능한지(모두 같은 수인지) 확인
        if (isCompressible(x, y, size, arr)) {
            // 모두 같다면 해당 숫자의 카운트 증가
            answer[arr[x][y]]++;
            
            return;
        }

        // 2. 압축이 불가능하다면 4개로 쪼개기
        int newSize = size / 2;
        
        // 왼쪽 위
        divide(x, y, newSize, arr);
        // 오른쪽 위
        divide(x, y + newSize, newSize, arr);
        // 왼쪽 아래
        divide(x + newSize, y, newSize, arr);
        // 오른쪽 아래
        divide(x + newSize, y + newSize, newSize, arr);
    }

    /* 현재 영역의 모든 원소가 같은지 체크하는 함수 */
    private boolean isCompressible(int x, int y, int size, int[][] arr) {
        
        int firstValue = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != firstValue) {
                    
                    return false;
                }
            }
        }
        
        return true;
    }
}