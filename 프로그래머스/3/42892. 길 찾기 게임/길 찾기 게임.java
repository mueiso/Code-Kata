import java.util.ArrayList;
import java.util.List;

class Solution {
    
    // 트리 노드 클래스
    static class Node {
        int id;
        int x;
        int y;
        Node left;
        Node right;

        Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    private int idx;

    public int[][] solution(int[][] nodeinfo) {
        
        int n = nodeinfo.length;
        List<Node> nodes = new ArrayList<>();

        // 1. 노드 객체 생성 (1번부터 시작하는 번호 부여)
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }

        // 2. y 좌표 내림차순 정렬 (y가 같으면 x 좌표 오름차순)
        nodes.sort((a, b) -> {
            if (a.y == b.y) {
                return Integer.compare(a.x, b.x);
            }
            return Integer.compare(b.y, a.y);
        });

        // 3. 가장 높은 노드를 루트로 설정 후 이진 트리 구축
        Node root = nodes.get(0);
        for (int i = 1; i < n; i++) {
            insertNode(root, nodes.get(i));
        }

        int[][] answer = new int[2][n];

        // 4. 전위 순회
        idx = 0;
        preOrder(root, answer[0]);

        // 5. 후위 순회
        idx = 0;
        postOrder(root, answer[1]);

        return answer;
    }

    /* BST 노드 삽입 함수 */
    private void insertNode(Node parent, Node child) {
        
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }

    /* 전위 순회 (Root -> Left -> Right)  */
    private void preOrder(Node node, int[] arr) {
        
        if (node == null) return;
        arr[idx++] = node.id;
        preOrder(node.left, arr);
        preOrder(node.right, arr);
    }

    /* 후위 순회 (Left -> Right -> Root) */
    private void postOrder(Node node, int[] arr) {
        
        if (node == null) return;
        postOrder(node.left, arr);
        postOrder(node.right, arr);
        arr[idx++] = node.id;
    }
}