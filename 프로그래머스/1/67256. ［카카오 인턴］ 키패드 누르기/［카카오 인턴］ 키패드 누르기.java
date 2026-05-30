class Solution {
    
    public String solution(int[] numbers, String hand) {
        
        StringBuilder sb = new StringBuilder();
        // 초기 엄지손가락 위치 (*, #)
        int leftPos = 10;  // *를 10으로 가정
        int rightPos = 12; // #를 12로 가정

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                sb.append("L");
                leftPos = num;
            } else if (num == 3 || num == 6 || num == 9) {
                sb.append("R");
                rightPos = num;
            } else {
                // 2, 5, 8, 0 인 경우 거리 비교
                if (num == 0) num = 11; // 0을 11번 위치로 매핑

                int leftDist = getDistance(leftPos, num);
                int rightDist = getDistance(rightPos, num);

                if (leftDist < rightDist) {
                    sb.append("L");
                    leftPos = num;
                } else if (rightDist < leftDist) {
                    sb.append("R");
                    rightPos = num;
                } else {
                    // 거리가 같을 때
                    if (hand.equals("left")) {
                        sb.append("L");
                        leftPos = num;
                    } else {
                        sb.append("R");
                        rightPos = num;
                    }
                }
            }
        }
        return sb.toString();
    }

    /* 두 키패드 번호 사이의 맨해튼 거리를 구하는 함수 */
    private int getDistance(int current, int target) {
        
        // 좌표 변환 (0~11 범위로 생각하기 위해 1을 뺌)
        int curX = (current - 1) / 3;
        int curY = (current - 1) % 3;
        int tarX = (target - 1) / 3;
        int tarY = (target - 1) % 3;

        return Math.abs(curX - tarX) + Math.abs(curY - tarY);
    }
}