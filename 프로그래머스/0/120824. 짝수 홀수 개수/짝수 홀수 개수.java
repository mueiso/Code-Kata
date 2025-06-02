class Solution {
    public int[] solution(int[] num_list) {
        int evenCount = 0;
        int oddCount = 0;

        for (int num : num_list) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        return new int[] {evenCount, oddCount};
    }
}
