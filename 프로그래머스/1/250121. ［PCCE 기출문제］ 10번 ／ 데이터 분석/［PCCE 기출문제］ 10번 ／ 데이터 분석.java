import java.util.*;

class Solution {
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        // 문자열을 컬럼 인덱스로 변환
        Map<String, Integer> map = new HashMap<>();
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);
        
        int extIndex = map.get(ext);
        int sortIndex = map.get(sort_by);
        
        // 조건 만족 데이터 저장용 리스트
        List<int[]> list = new ArrayList<>();
        
        // 필터링
        for(int[] row : data) {
            if(row[extIndex] < val_ext) {
                list.add(row);
            }
        }
        
        // 정렬 (sort_by 기준 오름차순)
        list.sort((a, b) -> Integer.compare(a[sortIndex], b[sortIndex]));
        
        // 리스트 -> 배열 변환
        int[][] answer = new int[list.size()][4];
        
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}