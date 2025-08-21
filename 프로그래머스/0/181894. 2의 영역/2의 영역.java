class Solution {
    
    public int[] solution(int[] arr) {
        
        int firstIndex = -1, lastIndex = -1;

        // 2의 첫 번째와 마지막 위치 찾기
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                if (firstIndex == -1) {
                    firstIndex = i;
                }
                lastIndex = i;
            }
        }

        // 2가 없는 경우
        if (firstIndex == -1) {
            return new int[]{-1};
        }

        // firstIndex ~ lastIndex까지 잘라서 붙이기
        int size = lastIndex - firstIndex + 1;
        int[] answer = new int[size];
        
        for (int i = 0; i < size; i++) {
            answer[i] = arr[firstIndex + i];
        }

        return answer;
    }
}
