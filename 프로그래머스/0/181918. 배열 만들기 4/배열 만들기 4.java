import java.util.ArrayList;

class Solution {
    
    public int[] solution(int[] arr) {
        
        // 초기화 (i = 0, stk = [])
        ArrayList<Integer> stk = new ArrayList<>();
        int i = 0;

        // i < arr.length 동안 다음 로직을 반복
        while (i < arr.length) {
            if (stk.isEmpty()) {
                // stk가 비었으면 현재 arr[i]를 추가하고 i 증가
                stk.add(arr[i]);
                i++;
            } else if (stk.get(stk.size() - 1) < arr[i]) {
                // stk가 비어있지 않고 stk 마지막 원소 < arr[i]면 추가하고 i 증가
                stk.add(arr[i]);
                i++;
            } else {
                // 그 외 (stk 마지막 원소 ≥ arr[i])인 경우, 마지막 원소 제거하고 i는 그대로 (비교 다시 해야 하므로)
                stk.remove(stk.size() - 1);
            }
        }

        // ArrayList → int[]로 변환
        return stk.stream().mapToInt(Integer::intValue).toArray();
    }
}
