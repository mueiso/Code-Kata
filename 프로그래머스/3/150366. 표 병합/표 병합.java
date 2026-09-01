import java.util.*;

class Solution {
    
    private int[] parent = new int[2500];
    private String[] values = new String[2500];

    public String[] solution(String[] commands) {
        
        // 유니온 파인드 및 값 배열 초기화
        for (int i = 0; i < 2500; i++) {
            parent[i] = i;
        }

        List<String> result = new ArrayList<>();

        for (String command : commands) {
            String[] tokens = command.split(" ");
            String type = tokens[0];

            if (type.equals("UPDATE")) {
                if (tokens.length == 4) {
                    int r = Integer.parseInt(tokens[1]);
                    int c = Integer.parseInt(tokens[2]);
                    String val = tokens[3];
                    
                    int root = find(getIndex(r, c));
                    values[root] = val;
                } else {
                    String val1 = tokens[1];
                    String val2 = tokens[2];
                    
                    for (int i = 0; i < 2500; i++) {
                        if (val1.equals(values[i])) {
                            values[i] = val2;
                        }
                    }
                }
            } else if (type.equals("MERGE")) {
                int r1 = Integer.parseInt(tokens[1]);
                int c1 = Integer.parseInt(tokens[2]);
                int r2 = Integer.parseInt(tokens[3]);
                int c2 = Integer.parseInt(tokens[4]);
                
                union(getIndex(r1, c1), getIndex(r2, c2));
            } else if (type.equals("UNMERGE")) {
                int r = Integer.parseInt(tokens[1]);
                int c = Integer.parseInt(tokens[2]);
                
                unmerge(getIndex(r, c));
            } else if (type.equals("PRINT")) {
                int r = Integer.parseInt(tokens[1]);
                int c = Integer.parseInt(tokens[2]);
                
                int root = find(getIndex(r, c));
                if (values[root] != null && !values[root].isEmpty()) {
                    result.add(values[root]);
                } else {
                    result.add("EMPTY");
                }
            }
        }

        return result.toArray(new String[0]);
    }

    private int getIndex(int r, int c) {
        return (r - 1) * 50 + (c - 1);
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        String valX = values[rootX];
        String valY = values[rootY];

        // rootX를 대표 루트 노드로 지정
        parent[rootY] = rootX;

        // 병합 시 우선순위: (r1, c1) 값 > (r2, c2) 값
        if (valX != null && !valX.isEmpty()) {
            values[rootX] = valX;
        } else if (valY != null && !valY.isEmpty()) {
            values[rootX] = valY;
        } else {
            values[rootX] = null;
        }

        values[rootY] = null; // 자식 루트의 값은 초기화
    }

    private void unmerge(int x) {
        
        int root = find(x);
        String targetValue = values[root];

        // 해제 대상 셀 수집
        List<Integer> targets = new ArrayList<>();
        for (int i = 0; i < 2500; i++) {
            if (find(i) == root) {
                targets.add(i);
            }
        }

        // 해당 셀들 병합 해제 및 데이터 초기화
        for (int cell : targets) {
            parent[cell] = cell;
            values[cell] = null;
        }

        // (r, c) 위치 셀에만 기존 값 복원
        values[x] = targetValue;
    }
}