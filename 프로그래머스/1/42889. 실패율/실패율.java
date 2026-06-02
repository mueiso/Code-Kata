import java.util.*;

class Solution {
    
    // 스테이지 번호와 실패율을 함께 관리하기 위한 클래스
    class Stage implements Comparable<Stage> {
        int id;
        double failureRate;

        Stage(int id, double failureRate) {
            this.id = id;
            this.failureRate = failureRate;
        }

        @Override
        public int compareTo(Stage o) {
            
            // 실패율이 같다면 스테이지 번호 오름차순
            if (this.failureRate == o.failureRate) {
                return Integer.compare(this.id, o.id);
            }
            
            // 실패율 기준 내림차순 정렬
            return Double.compare(o.failureRate, this.failureRate);
        }
    }

    public int[] solution(int N, int[] stages) {
        
        // 1. 각 스테이지에 머물러 있는 사용자 수 카운트
        int[] stageCount = new int[N + 2];
        for (int s : stages) {
            stageCount[s]++;
        }

        List<Stage> result = new ArrayList<>();
        double totalPlayers = stages.length; // 현재 스테이지에 도달한 총 인원

        // 2. 1번 스테이지부터 N번 스테이지까지 실패율 계산
        for (int i = 1; i <= N; i++) {
            if (totalPlayers == 0) {
                // 도달한 유저가 없으면 실패율은 0
                result.add(new Stage(i, 0.0));
            } else {
                double rate = stageCount[i] / totalPlayers;
                result.add(new Stage(i, rate));
                // 다음 스테이지 도달 인원 계산: 현재 스테이지에 머문 인원 제외
                totalPlayers -= stageCount[i];
            }
        }

        // 3. 정렬 조건에 맞춰 정렬 수행
        Collections.sort(result);

        // 4. 결과값(스테이지 번호) 추출
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = result.get(i).id;
        }

        return answer;
    }
}