/*
 * 다리는 최대 bridge_length칸 → 큐의 크기를 항상 bridge_length로 유지
 * 트럭이 없으면 0을 넣어서 시간 흐름만 표현
 * 현재 다리 위 트럭들의 총 무게가 weight를 넘지 않아야 다음 트럭 진입 가능
 * 모든 트럭이 올라간 뒤에도 마지막 트럭이 다리를 빠져나갈 시간이 필요
 */

import java.util.*;

class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> bridge = new LinkedList<>();
        int time = 0;
        int currentWeight = 0;

        // 처음에는 다리가 비어 있으므로 0으로 채워둔다
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        int index = 0; // 대기 트럭 인덱스

        while (index < truck_weights.length) {
            time++;

            // 다리에서 한 칸 이동 (앞의 트럭 제거)
            currentWeight -= bridge.poll();

            int nextTruck = truck_weights[index];

            // 다음 트럭이 올라갈 수 있는지 확인
            if (currentWeight + nextTruck <= weight) {
                bridge.add(nextTruck);
                currentWeight += nextTruck;
                index++;
            } else {
                // 못 올라가면 빈 공간 유지
                bridge.add(0);
            }
        }

        // 마지막 트럭이 다리를 완전히 건너는 시간 추가
        return time + bridge_length;
    }
}