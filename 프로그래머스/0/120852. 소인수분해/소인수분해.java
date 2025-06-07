import java.util.LinkedHashSet;

class Solution {
    public int[] solution(int n) {
        LinkedHashSet<Integer> primeNumbers = new LinkedHashSet<>();

        int i = 2;
        while (n != 0 && i <= n) {
            if (n % i == 0) {
                primeNumbers.add(i);
                n /= i;
            } else {
                i++;
            }
        }

        // System.out.println(primeNumbers);

        return primeNumbers.stream().mapToInt(Integer::intValue).toArray();
    }
}