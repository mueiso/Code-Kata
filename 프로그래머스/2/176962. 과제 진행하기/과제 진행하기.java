/*
 1. 시작 시간 순으로 정렬
 2. 새 과제가 시작되면 → 기존 과제 중단 (남은 시간 저장)
 3. 과제 끝나면 → 멈춘 과제 Stack에서 꺼내서 재개
 4. 여러 개 멈췄다면 → 가장 최근 것부터 (Stack)
 */

import java.util.*;

class Solution {
    
    public String[] solution(String[][] plans) {
        
        // 결과를 담을 리스트
        List<String> result = new ArrayList<>();
        
        // 과제를 시작 시간 기준으로 정렬
        Arrays.sort(plans, (a, b) -> convertTime(a[1]) - convertTime(b[1]));
        
        // 멈춘 과제를 저장할 스택 (최근 멈춘 과제부터 처리)
        Stack<Task> stack = new Stack<>();
        
        for (int i = 0; i < plans.length; i++) {
            
            String name = plans[i][0];
            int start = convertTime(plans[i][1]);
            int playtime = Integer.parseInt(plans[i][2]);
            
            // 현재 과제
            Task current = new Task(name, start, playtime);
            
            // 다음 과제 시작 시간
            int nextStart = (i < plans.length - 1)
                ? convertTime(plans[i + 1][1])
                : Integer.MAX_VALUE;
            
            // 현재 과제 끝나는 시간
            int endTime = current.start + current.remaining;
            
            if (endTime <= nextStart) {
                // 현재 과제를 끝낼 수 있는 경우
                result.add(current.name);
                
                int freeTime = nextStart - endTime;
                
                // 남는 시간 동안 멈춘 과제 처리
                while (!stack.isEmpty() && freeTime > 0) {
                    
                    Task paused = stack.pop();
                    
                    if (paused.remaining <= freeTime) {
                        freeTime -= paused.remaining;
                        result.add(paused.name);
                    } else {
                        paused.remaining -= freeTime;
                        stack.push(paused);
                        break;
                    }
                }
                
            } else {
                // 현재 과제를 끝내지 못하고 중단해야 하는 경우
                int remain = endTime - nextStart;
                current.remaining = remain;
                
                stack.push(current);
            }
        }
        
        // 남은 과제 처리 (스택 순서대로)
        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }
        
        return result.toArray(new String[0]);
    }
    
    /* "HH:MM" → 분으로 변환 */
    private int convertTime(String time) {
        
        String[] t = time.split(":");
        
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
    
    /* 과제 객체 */
    static class Task {
        
        String name;
        int start;
        int remaining;
        
        Task(String name, int start, int remaining) {
            
            this.name = name;
            this.start = start;
            this.remaining = remaining;
        }
    }
}