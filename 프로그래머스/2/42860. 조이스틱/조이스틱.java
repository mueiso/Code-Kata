class Solution {
    
    public int solution(String name) {
        
        int answer = 0;
        int length = name.length();

        // 가장 기본 이동 횟수 (쭉 오른쪽으로만 이동하는 경우)
        int minMove = length - 1;

        for (int i = 0; i < length; i++) {
            // 1. 알파벳 조작 횟수 누적
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);

            // 2. 커서 이동 횟수 계산을 위한 'A'의 끝점 탐색
            int next = i + 1;
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }

            // 오던 길로 되돌아가는 두 가지 경우의 수 중 최솟값으로 갱신
            // i: 오른쪽으로 온 거리 / (length - next): 왼쪽으로 역주행해야 하는 거리
            int toRightThenLeft = i * 2 + (length - next);
            int toLeftThenRight = (length - next) * 2 + i;

            minMove = Math.min(minMove, Math.min(toRightThenLeft, toLeftThenRight));
        }

        // 알파벳 변경 횟수 + 최종 커서 이동 최솟값
        return answer + minMove;
    }
}