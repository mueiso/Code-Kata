class Solution {
    
    public int solution(int n) {

        // 각 숫자가 소수인지 여부를 저장하는 배열
        boolean[] isPrime = new boolean[n + 1];

        // 2부터 n까지는 일단 모두 소수라고 가정
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        /*
         * 2부터 시작해서 소수의 배수들을 하나씩 지워나가는 과정
         * 어떤 수 i가 아직 지워지지 않았다면 (true라면) i는 소수
         * 따라서 i의 배수들은 모두 소수가 아니므로 false로 표시
        */
        for (int i = 2; i * i <= n; i++) {

            // 아직 지워지지 않았다면 i는 소수
            if (isPrime[i]) {

                /* i의 배수들을 모두 소수가 아닌 것으로 표시
                 * i*i 이전의 값들은 이미 이전 단계에서 지워졌기 때문에
                 * i*i부터 시작해도 충분하다
                 */
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 최종적으로 남아있는 소수의 개수를 센다
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }
}