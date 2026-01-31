/*
 * 메인 컨테이너 벨트: 1 → n 순서로만 나옴
 * 보조 컨테이너 벨트: 마지막에 넣은 상자부터 꺼냄 → Stack
 * 매 순간 할 수 있는 선택
 * 메인 벨트에서 다음 상자를 꺼냄
 * 보조 벨트(stack)의 top을 꺼냄
 * 현재 실어야 할 order와 일치하는 경우만 트럭에 적재
 * 둘 다 불가능하면 즉시 종료
 */

import java.util.*;

class Solution {
    
    public int solution(int[] order) {
        
        Stack<Integer> stack = new Stack<>();
        int box = 1;     // 메인 컨테이너에서 나올 상자 번호
        int idx = 0;     // order 인덱스
        int count = 0;

        while (true) {
            // 보조 컨테이너에서 바로 실을 수 있는 경우
            if (!stack.isEmpty() && stack.peek() == order[idx]) {
                stack.pop();
                idx++;
                count++;
            }
            // 메인 컨테이너에서 실을 수 있는 경우
            else if (box <= order.length) {
                if (box == order[idx]) {
                    box++;
                    idx++;
                    count++;
                } else {
                    // 바로 못 싣는 상자는 보조 컨테이너로 이동
                    stack.push(box);
                    box++;
                }
            }
            // 둘 다 불가능하면 종료
            else {
                break;
            }
        }

        return count;
    }
}