import java.util.*;

class Solution {
    public int solution(int[] array) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        for (int num : array) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int mode = -1;
        boolean duplicate = false;
        
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            
            if (value > maxFreq) {
                maxFreq = value;
                mode = key;
                duplicate = false;
            } else if (value == maxFreq) {
                duplicate = true;
            }
        }

        return duplicate ? -1 : mode;
    }
}
