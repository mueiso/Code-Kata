import java.util.*;

class Solution {
    public int solution(int[] array, int n) {
        Arrays.sort(array);
        int closest = array[0];
        
        for (int i = 1; i < array.length; i++) {
            int currentDiff = Math.abs(array[i] - n);
            int closestDiff = Math.abs(closest - n);
            
            if (currentDiff < closestDiff || (currentDiff == closestDiff && array[i] < closest)) {
                closest = array[i];
            }
        }
        
        return closest;
    }
}
