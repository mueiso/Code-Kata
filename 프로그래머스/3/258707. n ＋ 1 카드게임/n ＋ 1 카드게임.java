import java.util.*;

class Solution {
    
    public int solution(int coin, int[] cards) {
        
        int n = cards.length;
        int target = n + 1;

        Set<Integer> hand = new HashSet<>();
        Set<Integer> drawn = new HashSet<>();

        // 1. 처음에 n/3장의 카드를 가짐
        int idx = n / 3;
        for (int i = 0; i < idx; i++) {
            hand.add(cards[i]);
        }

        int round = 1;

        // 2. 라운드 진행
        while (idx < n) {
            // 이번 라운드 카드 2장 뽑아서 drawn에 보관
            drawn.add(cards[idx++]);
            drawn.add(cards[idx++]);

            boolean passed = false;

            // 우선순위 1: hand + hand (동전 0개 소모)
            int targetCard = -1;
            for (int card : hand) {
                if (hand.contains(target - card)) {
                    targetCard = card;
                    break;
                }
            }
            if (targetCard != -1) {
                hand.remove(targetCard);
                hand.remove(target - targetCard);
                passed = true;
            }

            // 우선순위 2: hand + drawn (동전 1개 소모)
            if (!passed && coin >= 1) {
                targetCard = -1;
                for (int card : hand) {
                    if (drawn.contains(target - card)) {
                        targetCard = card;
                        break;
                    }
                }
                if (targetCard != -1) {
                    hand.remove(targetCard);
                    drawn.remove(target - targetCard);
                    coin -= 1;
                    passed = true;
                }
            }

            // 우선순위 3: drawn + drawn (동전 2개 소모)
            if (!passed && coin >= 2) {
                targetCard = -1;
                for (int card : drawn) {
                    if (drawn.contains(target - card)) {
                        targetCard = card;
                        break;
                    }
                }
                if (targetCard != -1) {
                    drawn.remove(targetCard);
                    drawn.remove(target - targetCard);
                    coin -= 2;
                    passed = true;
                }
            }

            // 카드 쌍을 제출하지 못한 경우 게임 종료
            if (!passed) {
                break;
            }

            round++;
        }

        return round;
    }
}