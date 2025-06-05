class Solution {
    public int solution(int num, int k) {
        String numStr = String.valueOf(num);
        String kStr = String.valueOf(k);
        
        int index = numStr.indexOf(kStr);
        
        return index == -1 ? -1 : index + 1;
    }
}
