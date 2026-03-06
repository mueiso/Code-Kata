import java.util.*;

class Solution {
    
    public int solution(int[] nums) {

        // 중복 제거를 위한 Set
        Set<Integer> set = new HashSet<>();

        // 폰켓몬 종류 저장
        for (int num : nums) {
            set.add(num);
        }

        // 가져갈 수 있는 폰켓몬 수
        int maxPick = nums.length / 2;

        // 종류 수
        int typeCount = set.size();

        // 둘 중 작은 값 반환
        return Math.min(typeCount, maxPick);
    }
}