import java.util.*;

class Solution {
    
    public int[] solution(int n, int[] slicer, int[] num_list) {
        
        int a = slicer[0];
        int b = slicer[1];
        int c = slicer[2];
        
        int[] answer = {};
        
        switch (n) {
            case 1: // 0 ~ b
                answer = Arrays.copyOfRange(num_list, 0, b + 1);
                break;
            case 2: // a ~ 끝까지
                answer = Arrays.copyOfRange(num_list, a, num_list.length);
                break;
            case 3: // a ~ b
                answer = Arrays.copyOfRange(num_list, a, b + 1);
                break;
            case 4: // a ~ b, step = c
                List<Integer> list = new ArrayList<>();
                for (int i = a; i <= b; i += c) {
                    list.add(num_list[i]);
                }
                answer = list.stream().mapToInt(i -> i).toArray();
                break;
        }
        
        return answer;
    }
}
