import java.util.Arrays;

class Solution {
    
    public int solution(int[] people, int limit) {
        
        int answer = 0;
        
        // 1. 사람들의 몸무게를 오름차순 정렬
        Arrays.sort(people);
        
        // 2. 투 포인터 설정 (가장 가벼운 사람 index, 가장 무거운 사람 index)
        int left = 0;
        int right = people.length - 1;
        
        // 3. 모든 사람이 구조될 때까지 반복
        while (left <= right) {
            // 가장 무거운 사람과 가장 가벼운 사람이 같이 탈 수 있는지 확인
            if (people[left] + people[right] <= limit) {
                // 같이 탈 수 있다면 가벼운 사람 포인터도 이동
                left++;
            }
            
            // 무거운 사람은 무조건 이번 보트에 탐 (혼자 타든 같이 타든)
            right--;
            
            // 보트 개수 증가
            answer++;
        }
        
        return answer;
    }
}