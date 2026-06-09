import java.util.*;

class Solution {
    
    public int[] solution(String[] operations) {
        
        // key: 숫자, value: 해당 숫자의 빈도수 (중복 값 처리용)
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (String op : operations) {
            String[] parts = op.split(" ");
            String command = parts[0];
            int value = Integer.parseInt(parts[1]);

            // 1. 삽입 연산
            if (command.equals("I")) {
                map.put(value, map.getOrDefault(value, 0) + 1);
            } 
            // 2. 삭제 연산
            else if (command.equals("D")) {
                if (map.isEmpty()) continue; // 빈 큐인 경우 무시

                if (value == 1) {
                    // 최댓값 삭제
                    int maxKey = map.lastKey();
                    if (map.get(maxKey) == 1) {
                        map.remove(maxKey);
                    } else {
                        map.put(maxKey, map.get(maxKey) - 1);
                    }
                } else if (value == -1) {
                    // 최솟값 삭제
                    int minKey = map.firstKey();
                    if (map.get(minKey) == 1) {
                        map.remove(minKey);
                    } else {
                        map.put(minKey, map.get(minKey) - 1);
                    }
                }
            }
        }

        // 3. 최종 결과 반환
        if (map.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{map.lastKey(), map.firstKey()};
        }
    }
}