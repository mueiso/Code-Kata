class Solution {
    
    public int solution(String[] order) {
        
        int total = 0;
        
        for (String o : order) {
            if (o.contains("latte")) {
                total += 5000; // 라테
            } else {
                // 아메리카노 or "anything"
                total += 4500;
            }
        }
        
        return total;
    }
}