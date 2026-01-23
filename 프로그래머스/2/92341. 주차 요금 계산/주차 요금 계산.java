import java.util.*;

class Solution {
    
    public int[] solution(int[] fees, String[] records) {
        
        // 요금표 분리
        int baseTime = fees[0];   // 기본 시간
        int baseFee = fees[1];    // 기본 요금
        int unitTime = fees[2];   // 단위 시간
        int unitFee = fees[3];    // 단위 요금

        // 현재 입차 중인 차량의 입차 시각 저장
        Map<String, Integer> inMap = new HashMap<>();
        // 차량별 누적 주차 시간 저장
        Map<String, Integer> totalMap = new HashMap<>();

        // 입/출차 기록 처리
        for (String record : records) {
            String[] parts = record.split(" ");
            int time = toMinute(parts[0]); // 시각 → 분 단위 변환
            String car = parts[1];
            String type = parts[2];

            if (type.equals("IN")) {
                // 입차: 입차 시각 저장
                inMap.put(car, time);
            } else {
                // 출차: 주차 시간 누적
                int inTime = inMap.remove(car);
                totalMap.put(
                    car,
                    totalMap.getOrDefault(car, 0) + (time - inTime)
                );
            }
        }

        // 출차 기록이 없는 차량은 23:59에 출차된 것으로 처리
        int end = toMinute("23:59");
        
        for (String car : inMap.keySet()) {
            int inTime = inMap.get(car);
            totalMap.put(
                car,
                totalMap.getOrDefault(car, 0) + (end - inTime)
            );
        }

        // 차량 번호 오름차순 정렬
        List<String> cars = new ArrayList<>(totalMap.keySet());
        Collections.sort(cars);

        int[] answer = new int[cars.size()];
        int idx = 0;

        // 차량별 주차 요금 계산
        for (String car : cars) {
            int time = totalMap.get(car);
            int fee = baseFee;

            // 기본 시간 초과 시 단위 요금 계산 (올림 처리)
            if (time > baseTime) {
                fee += Math.ceil((time - baseTime) / (double) unitTime) * unitFee;
            }
            answer[idx++] = fee;
        }
        return answer;
    }

    // "HH:MM" 형식을 분 단위로 변환
    private int toMinute(String time) {
        
        String[] t = time.split(":");
        
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}