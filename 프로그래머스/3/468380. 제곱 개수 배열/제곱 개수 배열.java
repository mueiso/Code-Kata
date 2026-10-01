import java.util.*;

class Solution {
    
    private int N;
    private int[] arr;
    private long[] P;
    private long[] blockSumP;
    private long totalLen;

    public long[] solution(int[] arr, long l, long r) {
        
        this.arr = arr;
        this.N = arr.length;
        this.P = new long[N + 1];
        this.blockSumP = new long[N + 1];

        // 1. 블록 길이 누적 합(P) 및 블록 원소 합 누적 합(blockSumP) 구축
        for (int i = 0; i < N; i++) {
            P[i + 1] = P[i] + arr[i];
            blockSumP[i + 1] = blockSumP[i] + (long) arr[i] * arr[i];
        }

        this.totalLen = P[N];

        // 2. K 및 윈도우 길이 계산
        long K = getPrefixSumBrr(r) - getPrefixSumBrr(l - 1);
        long len = r - l + 1;
        long M = totalLen - len + 1;

        // 3. 임계점(Critical Points) 수집 및 정렬/중복 제거
        long[] cands = new long[2 * N + 4];
        int cSize = 0;

        cands[cSize++] = 1L;
        cands[cSize++] = M + 1;

        for (int i = 0; i <= N; i++) {
            long s1 = P[i] + 1;
            if (s1 >= 1 && s1 <= M + 1) {
                cands[cSize++] = s1;
            }
            long s2 = P[i] - len + 1;
            if (s2 >= 1 && s2 <= M + 1) {
                cands[cSize++] = s2;
            }
        }

        Arrays.sort(cands, 0, cSize);

        int qSize = 0;
        for (int i = 0; i < cSize; i++) {
            if (qSize == 0 || cands[qSize - 1] != cands[i]) {
                cands[qSize++] = cands[i];
            }
        }

        // 4. 선형 구간별 탐색으로 C 계산
        long C = 0;

        for (int rIdx = 0; rIdx < qSize - 1; rIdx++) {
            long a = cands[rIdx];
            long b = cands[rIdx + 1] - 1;
            if (a > b) continue;

            long Sa = getPrefixSumBrr(a + len - 1) - getPrefixSumBrr(a - 1);

            if (a == b) {
                if (Sa == K) {
                    C++;
                }
            } else {
                long vLeft = getValAt(a - 1);
                long vRight = getValAt(a + len - 1);
                long D = vRight - vLeft;

                if (D == 0) {
                    if (Sa == K) {
                        C += (b - a + 1);
                    }
                } else {
                    long delta = K - Sa;
                    if (delta % D == 0) {
                        long t = delta / D;
                        if (t >= 0 && t <= b - a) {
                            C++;
                        }
                    }
                }
            }
        }

        return new long[]{K, C};
    }

    /* brr의 1~X번째 원소들의 합을 구하는 이분 탐색 함수 */
    private long getPrefixSumBrr(long X) {
        
        if (X <= 0) return 0;
        if (X >= totalLen) return blockSumP[N];

        int low = 0, high = N;
        int idx = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (P[mid] <= X) {
                idx = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (P[idx] == X) {
            return blockSumP[idx];
        } else {
            return blockSumP[idx] + (X - P[idx]) * (long) arr[idx];
        }
    }

    /* brr의 0-based 인덱스 idx 위치의 원소 값을 구하는 이분 탐색 함수 */
    private long getValAt(long idx) {
        
        int low = 0, high = N - 1;
        int res = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (P[mid] <= idx) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return arr[res];
    }
}