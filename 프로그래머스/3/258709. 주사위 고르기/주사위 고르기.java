import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    
    private int n;
    private int half;
    private int[][] dice;
    private int maxWins = -1;
    private int[] bestCombination;

    public int[] solution(int[][] dice) {
        
        this.dice = dice;
        this.n = dice.length;
        this.half = n / 2;
        this.bestCombination = new int[half];

        // 1. A가 선택할 주사위 조합 구하기
        boolean[] selected = new boolean[n];
        selectDiceCombination(0, 0, selected);

        return bestCombination;
    }

    /* A의 주사위 조합 선택 (DFS) */
    private void selectDiceCombination(int start, int depth, boolean[] selected) {
        
        if (depth == half) {
            calculateWins(selected);
            return;
        }

        for (int i = start; i < n; i++) {
            selected[i] = true;
            selectDiceCombination(i + 1, depth + 1, selected);
            selected[i] = false;
        }
    }

    /* 현재 조합에서의 승리 횟수 계산 */
    private void calculateWins(boolean[] selected) {
        
        List<Integer> diceA = new ArrayList<>();
        List<Integer> diceB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (selected[i]) {
                diceA.add(i);
            } else {
                diceB.add(i);
            }
        }

        List<Integer> sumsA = new ArrayList<>();
        List<Integer> sumsB = new ArrayList<>();

        generateSums(0, 0, diceA, sumsA);
        generateSums(0, 0, diceB, sumsB);

        // B의 합 배열 정렬
        Collections.sort(sumsB);

        // A의 각 합에 대해 B보다 큰 경우의 수 이분 탐색으로 계산
        int totalWins = 0;
        for (int a : sumsA) {
            totalWins += countLessThan(sumsB, a);
        }

        // 최다 승리 횟수 갱신
        if (totalWins > maxWins) {
            maxWins = totalWins;
            for (int i = 0; i < half; i++) {
                bestCombination[i] = diceA.get(i) + 1; // 1-based index
            }
        }
    }

    /* 주사위들을 굴려 나올 수 있는 모든 합 생성 (DFS) */
    private void generateSums(int depth, int currentSum, List<Integer> diceIndices, List<Integer> sums) {
        
        if (depth == half) {
            sums.add(currentSum);
            return;
        }

        int diceIdx = diceIndices.get(depth);
        for (int val : dice[diceIdx]) {
            generateSums(depth + 1, currentSum + val, diceIndices, sums);
        }
    }

    /* target보다 작은 원소의 개수를 구하는 이분 탐색 (Lower Bound) */
    private int countLessThan(List<Integer> list, int target) {
        
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}