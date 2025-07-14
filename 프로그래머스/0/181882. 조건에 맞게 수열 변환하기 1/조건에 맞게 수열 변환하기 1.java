import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        
        return Arrays.stream(arr).map(operand -> operand >= 50
                                      && operand % 2 == 0 ? operand / 2 : operand < 50
                                      && operand % 2 == 1 ? operand * 2 : operand)
            .toArray();
    }
}