class Solution {
    
    public int solution(int n, int m, int[] section) {
        
        int answer = 0;

        // section은 이미 오름차순이라고 가정되지만,
        // 안전하게 하려면 정렬을 한 번 해도 됨.
        // Arrays.sort(section);

        int paintedEnd = 0; // 지금까지 롤러로 칠해진 마지막 구역 번호

        for (int s : section) {
            // 이 구역이 이미 칠해진 범위 안에 있으면 그냥 넘어간다.
            if (s <= paintedEnd) {
                continue;
            }

            // 이 구역부터 새로 롤러를 한 번 칠해야 함.
            answer++;

            // 롤러를 s부터 시작해서 길이 m만큼 칠하면
            // s ~ (s + m - 1) 구간이 새로 칠해진다.
            paintedEnd = s + m - 1;
        }

        return answer;
    }
}