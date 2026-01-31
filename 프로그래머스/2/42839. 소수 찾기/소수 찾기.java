/*
 * 모든 자리수 조합을 만들고, 중복 없이 소수만 세는 것이 핵심.
 * 문자열 numbers의 각 숫자를 중복 없이 사용해서 1자리 ~ n자리까지 모든 순열 생성
 * 만들어진 숫자를 Set에 저장 → 같은 숫자가 여러 번 만들어지는 경우는 제거
 * 각 숫자에 대해 소수 판별 (1 이하는 제외, 숫자 개수 최대 7개라서 √n 까지만 나눠보면 충분)
 */

import java.util.*;

class Solution {
    
    Set<Integer> set = new HashSet<>();
    boolean[] visited;

    public int solution(String numbers) {
        
        visited = new boolean[numbers.length()];

        // 모든 길이의 순열 생성
        for (int i = 1; i <= numbers.length(); i++) {
            dfs(numbers, "", i);
        }

        int count = 0;
        // 만들어진 숫자 중 소수 개수 세기
        for (int num : set) {
            if (isPrime(num)) {
                count++;
            }
        }

        return count;
    }

    // 순열 생성 DFS
    private void dfs(String numbers, String current, int targetLength) {
        
        if (current.length() == targetLength) {
            set.add(Integer.parseInt(current));
            
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(numbers, current + numbers.charAt(i), targetLength);
                visited[i] = false;
            }
        }
    }

    // 소수 판별
    private boolean isPrime(int num) {
        
        if (num < 2)
            return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}