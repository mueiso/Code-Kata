class Solution {
    
    public boolean solution(boolean x1, boolean x2, boolean x3, boolean x4) {
        
        boolean answer = (x1 || x2) && (x3 || x4);
        
        return answer;
    }
}

/*
 * ∨ : OR (또는) 연산 (||로 표현)
 * ∧ : AND (그리고) 연산 (&&로 표현)
 * 즉, Java에서 표현하면: (x1 ∨ x2) ∧ (x3 ∨ x4) → (x1 || x2) && (x3 || x4)
 */

/*
 * x1 || x2: x1 또는 x2 중 하나라도 true면 true
 * x3 || x4: x3 또는 x4 중 하나라도 true면 true
 * 두 결과를 && 연산으로 연결: 둘 다 true일 때만 전체 결과가 true
 */