import java.util.*;

class Solution {
    
    private static final int INF = 1_000_000;

    /* 세그먼트 트리 노드별 최소 힙 (원소: (time << 32) | rEnd)  */
    private static class LongHeap {
        
        long[] tree = new long[4];
        int size = 0;

        void push(long val) {
            if (size == tree.length) {
                tree = Arrays.copyOf(tree, tree.length * 2);
            }
            tree[size] = val;
            siftUp(size++);
        }

        long peek() {
            return tree[0];
        }

        void pop() {
            tree[0] = tree[--size];
            if (size > 0) siftDown(0);
        }

        boolean isEmpty() {
            return size == 0;
        }

        private void siftUp(int k) {
            long val = tree[k];
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                if (tree[parent] <= val) break;
                tree[k] = tree[parent];
                k = parent;
            }
            tree[k] = val;
        }

        private void siftDown(int k) {
            
            long val = tree[k];
            int half = size >>> 1;
            while (k < half) {
                int child = (k << 1) + 1;
                int right = child + 1;
                if (right < size && tree[right] < tree[child]) {
                    child = right;
                }
                if (val <= tree[child]) break;
                tree[k] = tree[child];
                k = child;
            }
            tree[k] = val;
        }
    }

    private int[] maxVal;
    private LongHeap[] heaps;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        
        int R = m - h + 1;
        int C = n - w + 1;
        int L = drops.length;
        int infinity = L + 1;

        // 세그먼트 트리 초기화
        maxVal = new int[4 * C];
        heaps = new LongHeap[4 * C];
        build(1, 0, C - 1, infinity);

        // 이벤트 연결 리스트 구축 (메모리 절약)
        int[] headAdd = new int[R];
        int[] nextAdd = new int[L];
        Arrays.fill(headAdd, -1);

        int[] headRemove = new int[R + 1];
        int[] nextRemove = new int[L];
        Arrays.fill(headRemove, -1);

        for (int i = 0; i < L; i++) {
            int rStart = Math.max(0, drops[i][0] - h + 1);
            int rEnd = Math.min(R - 1, drops[i][0]);

            nextAdd[i] = headAdd[rStart];
            headAdd[rStart] = i;

            if (rEnd + 1 <= R) {
                nextRemove[i] = headRemove[rEnd + 1];
                headRemove[rEnd + 1] = i;
            }
        }

        int bestVal = -1;
        int bestR = 0;
        int bestC = 0;

        // 행 스위핑
        for (int r = 0; r < R; r++) {
            // 1. 만료된 빗방울 제거
            for (int i = headRemove[r]; i != -1; i = nextRemove[i]) {
                int cStart = Math.max(0, drops[i][1] - w + 1);
                int cEnd = Math.min(C - 1, drops[i][1]);
                removeDrop(1, 0, C - 1, cStart, cEnd, r, infinity);
            }

            // 2. 신규 빗방울 추가
            for (int i = headAdd[r]; i != -1; i = nextAdd[i]) {
                int t = i + 1;
                int rEnd = Math.min(R - 1, drops[i][0]);
                long dropEntry = ((long) t << 32) | rEnd;

                int cStart = Math.max(0, drops[i][1] - w + 1);
                int cEnd = Math.min(C - 1, drops[i][1]);
                addDrop(1, 0, C - 1, cStart, cEnd, dropEntry, r, infinity);
            }

            // 3. 현재 행 최댓값 확인 및 갱신
            int currentMax = maxVal[1];
            if (currentMax > bestVal) {
                bestVal = currentMax;
                bestR = r;
                bestC = findLeftmost(1, 0, C - 1, infinity, currentMax, r, infinity);
            }
        }

        return new int[]{bestR, bestC};
    }

    private void build(int u, int start, int end, int infinity) {
        
        maxVal[u] = infinity;
        if (start == end) return;
        int mid = (start + end) >>> 1;
        build(u << 1, start, mid, infinity);
        build((u << 1) | 1, mid + 1, end, infinity);
    }

    private int getTagMin(int u, int r, int infinity) {
        
        LongHeap heap = heaps[u];
        if (heap == null) return infinity;
        while (!heap.isEmpty()) {
            long top = heap.peek();
            int rEnd = (int) (top & 0xFFFFFFFFL);
            if (rEnd < r) {
                heap.pop();
            } else {
                return (int) (top >>> 32);
            }
        }
        
        return infinity;
    }

    private void updateNode(int u, int r, int start, int end, int infinity) {
        
        int tag = getTagMin(u, r, infinity);
        if (start == end) {
            maxVal[u] = tag;
        } else {
            int left = u << 1;
            int right = left | 1;
            int childrenMax = Math.max(maxVal[left], maxVal[right]);
            maxVal[u] = Math.min(tag, childrenMax);
        }
    }

    private void addDrop(int u, int start, int end, int ql, int qr, long dropEntry, int r, int infinity) {
        
        if (ql <= start && end <= qr) {
            if (heaps[u] == null) heaps[u] = new LongHeap();
            heaps[u].push(dropEntry);
            updateNode(u, r, start, end, infinity);
            return;
        }
        int mid = (start + end) >>> 1;
        int left = u << 1;
        int right = left | 1;
        if (ql <= mid) addDrop(left, start, mid, ql, qr, dropEntry, r, infinity);
        if (qr > mid) addDrop(right, mid + 1, end, ql, qr, dropEntry, r, infinity);
        updateNode(u, r, start, end, infinity);
    }

    private void removeDrop(int u, int start, int end, int ql, int qr, int r, int infinity) {
        
        if (ql <= start && end <= qr) {
            updateNode(u, r, start, end, infinity);
            return;
        }
        int mid = (start + end) >>> 1;
        int left = u << 1;
        int right = left | 1;
        if (ql <= mid) removeDrop(left, start, mid, ql, qr, r, infinity);
        if (qr > mid) removeDrop(right, mid + 1, end, ql, qr, r, infinity);
        updateNode(u, r, start, end, infinity);
    }

    private int findLeftmost(int u, int start, int end, int currentMinTag, int targetVal, int r, int infinity) {
        
        int tag = getTagMin(u, r, infinity);
        int nodeMin = Math.min(currentMinTag, tag);

        if (start == end) return start;

        int mid = (start + end) >>> 1;
        int left = u << 1;
        int right = left | 1;

        int leftMax = Math.min(nodeMin, maxVal[left]);
        if (leftMax == targetVal) {
            return findLeftmost(left, start, mid, nodeMin, targetVal, r, infinity);
        } else {
            return findLeftmost(right, mid + 1, end, nodeMin, targetVal, r, infinity);
        }
    }
}