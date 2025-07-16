import java.util.*;

class Solution {
    
    public String[] solution(String[] names) {
        
        List<String> result = new ArrayList<>();

        for (int i = 0; i < names.length; i += 5) {
            result.add(names[i]);  // 각 그룹의 첫 번째 사람
        }

        return result.toArray(new String[0]);
    }
}
