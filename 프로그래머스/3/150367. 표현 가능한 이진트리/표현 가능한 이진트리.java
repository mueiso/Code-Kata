class Solution {
    
    public int[] solution(long[] numbers) {
        
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            
            // 1. 포화 이진트리의 노드 개수(2^h - 1) 계산
            int len = binary.length();
            int treeLen = 1;
            while (treeLen < len) {
                treeLen = treeLen * 2 + 1; // 1, 3, 7, 15, 31, 63...
            }

            // 2. 앞에 '0' 패딩 추가
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < treeLen - len; j++) {
                sb.append('0');
            }
            sb.append(binary);

            // 3. 트리가 유효한지 재귀적으로 검증
            String fullTree = sb.toString();
            if (isValidTree(fullTree, 0, fullTree.length() - 1, false)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private boolean isValidTree(String tree, int start, int end, boolean isParentDummy) {
        
        if (start > end) {
            return true;
        }

        int mid = (start + end) / 2;
        char root = tree.charAt(mid);

        // 부모가 더미('0')인데 현재 노드가 실제 노드('1')라면 유효하지 않은 이진트리
        if (isParentDummy && root == '1') {
            return false;
        }

        // 현재 노드가 더미이거나, 이미 부모가 더미였던 경우 자식 노드에게 더미 상태 전달
        boolean currentIsDummy = isParentDummy || (root == '0');

        // 왼쪽 서브트리와 오른쪽 서브트리 검사
        return isValidTree(tree, start, mid - 1, currentIsDummy) 
            && isValidTree(tree, mid + 1, end, currentIsDummy);
    }
}