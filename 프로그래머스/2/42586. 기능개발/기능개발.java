import java.util.ArrayList;
import java.util.List;

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {

        List<Integer> result = new ArrayList<>();

        // 첫 기능의 완료까지 필요한 날짜 계산
        int prevDay = (int) Math.ceil((100 - progresses[0]) / (double) speeds[0]);
        int count = 1;

        // 두 번째 기능부터 순차적으로 확인
        for (int i = 1; i < progresses.length; i++) {

            // 현재 기능의 완료 날짜
            int currentDay = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);

            // 이전 배포 날짜보다 빨리 끝나면 같이 배포
            if (currentDay <= prevDay) {
                count++;
            } 
            // 더 오래 걸리면 이전 배포 마감
            else {
                result.add(count);
                count = 1;
                prevDay = currentDay;
            }
        }

        // 마지막 배포 추가
        result.add(count);

        // List → 배열 변환
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}