class Solution {
    
    public String solution(int[] food) {
        
        // 왼쪽 선수 쪽에 놓일 음식들을 쌓을 StringBuilder
        StringBuilder left = new StringBuilder();

        // 왼쪽 음식 배열 구성
        // 0번 인덱스는 물이기 때문에 1번 음식부터 반복
        for (int i = 1; i < food.length; i++) {
            int count = food[i] / 2;  // 두 선수가 공평하게 나눠 먹어야 하므로, 한쪽 선수에게 줄 개수는 절반

            // 해당 음식 번호(i)를 count번만큼 왼쪽에 추가
            for (int j = 0; j < count; j++) {
                left.append(i);  // 낮은 칼로리 순(번호 작은 것부터)으로 음식 번호 추가
            }
        }

        // 오른쪽은 왼쪽을 뒤집은 것(거울 대칭)
        // left 내용을 복사한 뒤 reverse() 해서 문자열로 변환
        String right = new StringBuilder(left).reverse().toString();

        // 물(0)을 가운데에 두고, 왼쪽 + 0 + 오른쪽 순서로 배치한 최종 문자열 반환
        return left.toString() + "0" + right;
    }
}