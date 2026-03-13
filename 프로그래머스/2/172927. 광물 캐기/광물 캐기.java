import java.util.*;

class Solution {

    public int solution(int[] picks, String[] minerals) {

        int answer = 0;

        // 총 사용할 수 있는 곡괭이 개수
        int pickCount = picks[0] + picks[1] + picks[2];

        // 광물을 5개씩 그룹으로 저장
        List<int[]> groups = new ArrayList<>();

        for (int i = 0; i < minerals.length; i += 5) {

            if (groups.size() == pickCount) break;

            int diamond = 0;
            int iron = 0;
            int stone = 0;

            for (int j = i; j < i + 5 && j < minerals.length; j++) {

                if (minerals[j].equals("diamond")) {
                    diamond++;
                } else if (minerals[j].equals("iron")) {
                    iron++;
                } else stone++;
            }

            groups.add(new int[]{diamond, iron, stone});
        }

        // 난이도 기준 정렬 (돌 곡괭이 기준 피로도)
        Collections.sort(groups, (a, b) -> {

            int stonePickFatigueA = a[0] * 25 + a[1] * 5 + a[2];
            int stonePickFatigueB = b[0] * 25 + b[1] * 5 + b[2];

            return stonePickFatigueB - stonePickFatigueA;
        });

        // 좋은 곡괭이부터 사용
        for (int[] g : groups) {

            int diamond = g[0];
            int iron = g[1];
            int stone = g[2];

            // 다이아 곡괭이
            if (picks[0] > 0) {

                answer += diamond + iron + stone;
                picks[0]--;

            }
            
            // 철 곡괭이
            else if (picks[1] > 0) {

                answer += diamond * 5 + iron + stone;
                picks[1]--;

            }
            
            // 돌 곡괭이
            else {

                answer += diamond * 25 + iron * 5 + stone;
                picks[2]--;
            }
        }

        return answer;
    }
}