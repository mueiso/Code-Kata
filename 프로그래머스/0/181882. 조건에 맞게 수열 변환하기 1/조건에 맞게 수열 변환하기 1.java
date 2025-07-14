class Solution {
    public int[] solution(int[] arr) {
        
        int[] answer = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num >= 50 && num % 2 == 0) {
                answer[i] = num / 2;
            } else if (num < 50 && num % 2 == 1) {
                answer[i] = num * 2;
            } else {
                answer[i] = num;
            }
        }

        return answer;
    }
}
