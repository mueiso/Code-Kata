class Solution {

    private final int[] discounts = {10, 20, 30, 40};  // 가능한 할인율
    private int maxSubscriber = 0;                     // 최종 최대 가입자 수
    private int maxSales = 0;                          // 최종 최대 매출

    public int[] solution(int[][] users, int[] emoticons) {

        // 각 이모티콘 할인율 저장 배열
        int[] selected = new int[emoticons.length];

        // 모든 할인 조합 탐색
        dfs(0, selected, users, emoticons);

        return new int[]{maxSubscriber, maxSales};
    }

    /* DFS로 할인율 조합 생성 */
    private void dfs(int depth, int[] selected,
                     int[][] users, int[] emoticons) {

        // 모든 이모티콘 할인율 선택 완료
        if (depth == emoticons.length) {

            calculate(selected, users, emoticons);
            return;
        }

        // 10,20,30,40 할인 적용
        for (int discount : discounts) {

            selected[depth] = discount;

            dfs(depth + 1, selected, users, emoticons);
        }
    }

    /* 현재 할인 조합 결과 계산 */
    private void calculate(int[] selected,
                           int[][] users,
                           int[] emoticons) {

        int subscriber = 0;
        int sales = 0;

        // 모든 사용자 확인
        for (int[] user : users) {

            int needDiscount = user[0];
            int limitPrice = user[1];

            int total = 0;

            // 구매 가능한 이모티콘 계산
            for (int i = 0; i < emoticons.length; i++) {

                // 사용자의 기준 할인율 이상이면 구매
                if (selected[i] >= needDiscount) {

                    total += emoticons[i]
                            * (100 - selected[i]) / 100;
                }
            }

            // 기준 금액 이상이면 플러스 가입
            if (total >= limitPrice) {

                subscriber++;
            }

            // 아니면 매출 추가
            else {
                sales += total;
            }
        }

        // 가입자 수 우선 비교
        if (subscriber > maxSubscriber) {

            maxSubscriber = subscriber;
            maxSales = sales;
        }

        // 가입자 수 같으면 매출 비교
        else if (subscriber == maxSubscriber) {

            maxSales = Math.max(maxSales, sales);
        }
    }
}