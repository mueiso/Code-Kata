class Solution {
    public String solution(int[] food) {
        StringBuilder left = new StringBuilder();

        // 왼쪽 음식 배열 구성
        for (int i = 1; i < food.length; i++) {
            int count = food[i] / 2;  // 선수당 먹을 수 있는 음식 수
            for (int j = 0; j < count; j++) {
                left.append(i);  // 낮은 칼로리 순으로 음식 번호 추가
            }
        }

        // 오른쪽은 왼쪽을 뒤집은 것
        String right = new StringBuilder(left).reverse().toString();

        // 물(0)을 가운데에 넣고 양쪽 음식 배치
        return left.toString() + "0" + right;
    }
}