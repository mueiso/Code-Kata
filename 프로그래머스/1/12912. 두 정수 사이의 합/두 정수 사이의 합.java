class Solution {
    
    public long solution(int a, int b) {
        
        long answer = 0;
        
        // a가 더 크면 두 값을 바꿔서 항상 a <= b가 되도록
        if (a > b) {
            int tempValue = a;
            a = b;
            b = tempValue;
        }
        
        for (int i = a; i <= b; i++) {
            answer += i;
            
            if (a == b) {
            return a;
        }
    }

        return answer;
    }
}