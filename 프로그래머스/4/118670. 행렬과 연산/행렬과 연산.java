import java.util.*;

class Solution {
    
    public int[][] solution(int[][] rc, String[] operations) {
        
        int r = rc.length;
        int c = rc[0].length;

        Deque<Integer> leftCol = new ArrayDeque<>();
        Deque<Integer> rightCol = new ArrayDeque<>();
        Deque<Deque<Integer>> rows = new ArrayDeque<>();

        // 1. 행렬을 왼쪽 열, 오른쪽 열, 중간 행들로 분리하여 덱에 저장
        for (int i = 0; i < r; i++) {
            leftCol.addLast(rc[i][0]);
            rightCol.addLast(rc[i][c - 1]);
            Deque<Integer> midRow = new ArrayDeque<>();
            for (int j = 1; j < c - 1; j++) {
                midRow.addLast(rc[i][j]);
            }
            rows.addLast(midRow);
        }

        // 2. 연산 수행
        for (String op : operations) {
            if (op.equals("ShiftRow")) {
                rows.addFirst(rows.pollLast());
                leftCol.addFirst(leftCol.pollLast());
                rightCol.addFirst(rightCol.pollLast());
            } else { // "Rotate"
                if (c == 2) {
                    rightCol.addFirst(leftCol.pollFirst());
                    leftCol.addLast(rightCol.pollLast());
                } else {
                    Deque<Integer> topRow = rows.peekFirst();
                    Deque<Integer> bottomRow = rows.peekLast();

                    rightCol.addFirst(topRow.pollLast());
                    bottomRow.addLast(rightCol.pollLast());
                    leftCol.addLast(bottomRow.pollFirst());
                    topRow.addFirst(leftCol.pollFirst());
                }
            }
        }

        // 3. 결과를 다시 2차원 배열로 조합
        int[][] answer = new int[r][c];
        int idx = 0;
        
        while (!rows.isEmpty()) {
            Deque<Integer> midRow = rows.pollFirst();
            answer[idx][0] = leftCol.pollFirst();
            for (int j = 1; j < c - 1; j++) {
                answer[idx][j] = midRow.pollFirst();
            }
            answer[idx][c - 1] = rightCol.pollFirst();
            idx++;
        }

        return answer;
    }
}