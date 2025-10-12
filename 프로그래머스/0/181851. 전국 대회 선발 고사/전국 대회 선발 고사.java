import java.util.*;

class Solution {
    
    public int solution(int[] rank, boolean[] attendance) {
        
        // 1. (등수, 학생번호) 쌍을 저장할 리스트
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < rank.length; i++) {
            if (attendance[i]) { // 참여 가능한 학생만
                list.add(new int[]{rank[i], i});
            }
        }

        // 2. 등수 기준 오름차순 정렬
        list.sort(Comparator.comparingInt(a -> a[0]));

        // 3. 상위 3명의 학생번호 추출
        int a = list.get(0)[1];
        int b = list.get(1)[1];
        int c = list.get(2)[1];

        // 4. 문제에서 주어진 식 계산
        return 10000 * a + 100 * b + c;
    }
}