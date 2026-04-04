/*
 * 친구 이름을 인덱스(0, 1, 2...)로 매핑하여 배열이나 행렬에 쉽게 접근할 수 있도록 한다.
 * 2차원 배열을 사용해 누가 누구에게 선물을 몇 번 줬는지 기록한다.
 * 각 친구의 선물 지수를 계산하기 위해 준 선물 총합과 받은 선물 총합을 저장한다.
 * 모든 친구 쌍을 비교하며 문제의 규칙(기록 비교 -> 선물 지수 비교)에 따라 다음 달 받을 선물을 카운트한다.
 */

import java.util.*;

class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        
        int n = friends.length;
        
        /*
         * 친구 이름과 인덱스를 매핑한다. 
         * 이름으로 배열 인덱스를 빠르게 찾기 위해 HashMap을 사용한다.
         */
        Map<String, Integer> friendIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            friendIndex.put(friends[i], i);
        }

        /*
         * 선물 주고받은 내역을 저장할 2차원 배열과 선물 지수를 계산할 배열들을 선언한다.
         * matrix[i][j]는 i가 j에게 준 선물의 개수를 의미한다.
         */
        int[][] matrix = new int[n][n];
        int[] given = new int[n];
        int[] received = new int[n];

        /*
         * 이번 달 선물 기록을 순회하며 데이터를 정리한다.
         * "준사람 받은사람" 형태의 문자열을 분리하여 각 배열에 기록한다.
         */
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            int giverIdx = friendIndex.get(split[0]);
            int receiverIdx = friendIndex.get(split[1]);

            matrix[giverIdx][receiverIdx]++;
            given[giverIdx]++;
            received[receiverIdx]++;
        }

        /*
         * 선물 지수를 미리 계산해둔다.
         * 선물 지수 = 준 선물 수 - 받은 선물 수
         */
        int[] giftIndex = new int[n];
        for (int i = 0; i < n; i++) {
            giftIndex[i] = given[i] - received[i];
        }

        int maxGifts = 0;

        /*
         * 모든 친구 쌍을 비교하여 다음 달에 받을 선물의 수를 계산한다.
         * 각 친구(i)가 다른 모든 친구(j)와 비교했을 때 선물을 받는지 확인한다.
         */
        for (int i = 0; i < n; i++) {
            int currentFriendReceive = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // 자기 자신과는 비교하지 않는다.

                // 규칙1. 두 사람 사이에 선물을 주고받은 기록이 있고, 준 횟수가 다를 때 더 많이 준 사람이 선물을 하나 받는다.
                if (matrix[i][j] > matrix[j][i]) {
                    currentFriendReceive++;
                } 
                // 규칙2. 기록이 없거나 주고받은 횟수가 같을 때 선물 지수가 더 큰 사람이 선물을 하나 받는다.
                else if (matrix[i][j] == matrix[j][i]) {
                    if (giftIndex[i] > giftIndex[j]) {
                        currentFriendReceive++;
                    }
                }
            }
            // 가장 많이 받는 친구의 선물 개수를 갱신한다.
            maxGifts = Math.max(maxGifts, currentFriendReceive);
        }

        return maxGifts;
    }
}