import java.util.*;

class Solution {
    
    public int[] solution(int[] arr, int[] delete_list) {
        
        Set<Integer> deleteSet = new HashSet<>();
        for (int d : delete_list) {
            deleteSet.add(d);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            if (!deleteSet.contains(num)) {
                list.add(num);
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}