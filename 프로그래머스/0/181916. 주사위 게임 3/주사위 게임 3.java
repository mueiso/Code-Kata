import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
        // 숫자 빈도수 저장용 Map
        Map<Integer, Integer> count = new HashMap<>();

        // 각 숫자의 빈도 수 세기
        for (int num : new int[]{a, b, c, d}) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // Map의 keySet을 리스트로 저장 (숫자 종류)
        List<Integer> keys = new ArrayList<>(count.keySet());

        // 조건 1: 4개 모두 같은 경우
        if (count.size() == 1) {
            int p = keys.get(0);
            return 1111 * p;
        }

        // 조건 2: 3개 같고 1개 다른 경우
        if (count.size() == 2) {
            int p = keys.get(0), q = keys.get(1);
            int cp = count.get(p), cq = count.get(q);

            // 3개와 1개로 나뉘는 경우
            if (cp == 3 || cq == 3) {
                int three = (cp == 3) ? p : q;
                int one = (cp == 3) ? q : p;
                return (int) Math.pow((10 * three + one), 2);
            }

            // 조건 3: 2개씩 같은 경우 (2,2)
            return (p + q) * Math.abs(p - q);
        }

        // 조건 4: 2개 같고 나머지 두 수가 다를 때 (2,1,1)
        if (count.size() == 3) {
            int pair = 0, q = 0, r = 0;
            for (int key : keys) {
                int cnt = count.get(key);
                if (cnt == 2) {
                    pair = key;
                } else if (q == 0) {
                    q = key;
                } else {
                    r = key;
                }
            }
            return q * r;
        }

        // 조건 5: 모두 다를 때
        return Collections.min(keys);
    }
}

/*
 * 조건 1: 4개 모두 같은 경우
   count.size() == 1이면 4개 숫자 모두 동일
 * 점수 = 1111 × p

 * 조건 2: 3개 같은 수 + 1개 다른 수
   count.size() == 2인데, 하나는 3번 등장, 하나는 1번 등장
 * 점수 = (10 × p + q)^2
   (p: 3번 나온 수, q: 1번 나온 수)

 * 조건 3: 2개씩 같은 수
   count.size() == 2이지만 두 수 모두 2번 등장
 * 점수 = (p + q) × |p - q|

 * 조건 4: 하나가 2번, 두 개가 1번씩
   count.size() == 3
 * 점수 = q × r (q, r: 1번씩 나온 수)

 * 조건 5: 모두 다름
   count.size() == 4
 * 점수 = 가장 작은 수
 */