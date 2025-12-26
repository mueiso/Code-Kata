import java.util.*;

class Solution {
    
    public String[] solution(String[] players, String[] callings) {

        // 선수 이름 -> 현재 인덱스(등수)
        Map<String, Integer> pos = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            pos.put(players[i], i);
        }

        for (String name : callings) {
            int i = pos.get(name);     // 호출된 선수의 현재 위치
            int j = i - 1;             // 바로 앞 선수 위치

            // 바로 앞 선수 이름
            String front = players[j];

            // 배열에서 두 선수 자리 바꾸기
            players[j] = name;
            players[i] = front;

            // 맵에서도 위치 갱신
            pos.put(name, j);
            pos.put(front, i);
        }

        return players;
    }
}