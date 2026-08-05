import java.util.Arrays;

class Solution {
    
    public int solution(int[][] routes) {
        
        // 1. 차량의 진출 지점(routes[i][1]) 기준 오름차순 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int answer = 0;
        // 카메라 설치 위치 초기화 (진입 최소 지점 -30,000보다 작게 설정)
        int camera = -30001;

        for (int[] route : routes) {
            // 현재 차량의 진입 지점이 마지막 설치된 카메라보다 뒤에 있으면 단속 불가
            if (route[0] > camera) {
                answer++;           // 새 카메라 설치
                camera = route[1];  // 카메라 위치를 현재 차량의 진출 지점으로 갱신
            }
        }

        return answer;
    }
}