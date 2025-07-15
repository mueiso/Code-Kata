class Solution {
    
    public int solution(int[] num_list) {
        
        StringBuilder odd = new StringBuilder();
        StringBuilder even = new StringBuilder();

        for (int num : num_list) {
            if (num % 2 == 0) {
                even.append(num);
            } else {
                odd.append(num);
            }
        }

        int oddNum = odd.length() == 0 ? 0 : Integer.parseInt(odd.toString());
        int evenNum = even.length() == 0 ? 0 : Integer.parseInt(even.toString());

        return oddNum + evenNum;
    }
}
