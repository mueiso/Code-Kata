import java.util.*;

class Solution {
    
    public int[] solution(int l, int r) {
        
        List<Integer> result = new ArrayList<>();

        for (int i = l; i <= r; i++) {
            String s = String.valueOf(i);
            boolean isValid = true;

            for (char c : s.toCharArray()) {
                if (c != '0' && c != '5') {
                    isValid = false;
                    break;
                }
            }

            if (isValid) result.add(i);
        }

        if (result.isEmpty()) {
            return new int[]{-1};
        }

        // 리스트를 배열로 변환
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
