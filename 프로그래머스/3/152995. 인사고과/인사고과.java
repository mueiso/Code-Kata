import java.util.Arrays;

class Solution {
    
    public int solution(int[][] scores) {
        
        // 1. 완호의 점수와 점수 합 기록
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;
        
        // 2. 정렬 (근무 태도 내림차순, 동료 평가 오름차순)
        Arrays.sort(scores, (s1, s2) -> {
            if (s1[0] == s2[0]) {
                return s1[1] - s2[1]; // 동료 평가는 오름차순
            }
            return s2[0] - s1[0];     // 근무 태도는 내림차순
        });
        
        int maxPeerScore = 0;
        int rank = 1;
        
        // 3. 순회하며 탈락자 필터링 및 석차 계산
        for (int[] score : scores) {
            // 내 동료 평가 점수가 여태까지의 최댓값보다 작다면 탈락
            if (score[1] < maxPeerScore) {
                // 탈락자가 완호라면 인센티브를 못 받으므로 -1 반환
                if (score[0] == wanhoA && score[1] == wanhoB) {
                    return -1;
                }
            } else {
                // 탈락하지 않은 사원이라면
                // 동료 평가 점수 최댓값 갱신
                maxPeerScore = Math.max(maxPeerScore, score[1]);
                
                // 완호보다 점수 합이 높다면 완호의 등수가 1씩 밀려남
                if (score[0] + score[1] > wanhoSum) {
                    rank++;
                }
            }
        }
        
        return rank;
    }
}