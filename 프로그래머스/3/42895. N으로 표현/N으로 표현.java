import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    
    public int solution(int N, int number) {
        
        if (N == number) {
            return 1;
        }

        // countSet.get(i)는 N을 i번 사용해서 만들 수 있는 수들의 집합
        List<Set<Integer>> countSet = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            countSet.add(new HashSet<>());
        }

        // N을 1번 사용한 경우
        countSet.get(1).add(N);

        for (int i = 1; i <= 8; i++) {
            // 1. N을 i번 이어 붙인 수 추가 (예: 5, 55, 555...)
            int num = 0;
            for (int k = 0; k < i; k++) {
                num = num * 10 + N;
            }
            countSet.get(i).add(num);

            // 2. j번 사용한 집합과 (i - j)번 사용한 집합의 사칙연산 조합
            for (int j = 1; j < i; j++) {
                Set<Integer> setA = countSet.get(j);
                Set<Integer> setB = countSet.get(i - j);

                for (int a : setA) {
                    for (int b : setB) {
                        countSet.get(i).add(a + b);
                        countSet.get(i).add(a - b);
                        countSet.get(i).add(a * b);
                        if (b != 0) {
                            countSet.get(i).add(a / b);
                        }
                    }
                }
            }

            // 목표 숫자(number)가 만들어졌다면 최솟값 i 반환
            if (countSet.get(i).contains(number)) {
                return i;
            }
        }

        // 8번 이하로 만들 수 없는 경우
        return -1;
    }
}