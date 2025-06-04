class Solution {
    public int solution(int[] box, int n) {
        int countX = box[0] / n;
        int countY = box[1] / n;
        int countZ = box[2] / n;
        
        return countX * countY * countZ;
    }
}
