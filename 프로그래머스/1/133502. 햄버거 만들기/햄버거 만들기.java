class Solution {
    
    public int solution(int[] ingredient) {
        
        int answer = 0;

        int[] stack = new int[ingredient.length];
        int top = 0; // stack에 쌓인 개수(다음 삽입 위치)

        for (int x : ingredient) {
            stack[top++] = x; // 재료 쌓기

            // 끝 4개가 1,2,3,1이면 햄버거 1개 포장
            if (top >= 4 &&
                stack[top - 4] == 1 &&
                stack[top - 3] == 2 &&
                stack[top - 2] == 3 &&
                stack[top - 1] == 1) {

                top -= 4;   // 햄버거 재료 4개 제거(pop)
                answer++;   // 포장 개수 증가
            }
        }

        return answer;
    }
}