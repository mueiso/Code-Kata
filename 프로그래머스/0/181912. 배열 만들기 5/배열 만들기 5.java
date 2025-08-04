import java.util.ArrayList;

class Solution {

    public int[] solution(String[] intStrs, int k, int s, int l) {

        // 조건을 만족하는 정수를 담을 리스트 생성
        ArrayList<Integer> list = new ArrayList<>();

        // 주어진 문자열 배열을 순회
        for (int i = 0; i < intStrs.length; i++) {

            // 각 문자열에서 s번째 인덱스부터 길이 l만큼 부분 문자열을 추출
            String subStr = intStrs[i].substring(s, s + l);

            // 부분 문자열을 정수로 변환
            int tmp = Integer.parseInt(subStr);

            // 변환한 정수가 k보다 클 경우에만 리스트에 추가
            if (tmp > k) {
                list.add(tmp);
            }
        }

        // 리스트에 저장된 결과를 int 배열로 변환
        int[] answer = new int[list.size()];

        // 리스트에서 하나씩 꺼내서 배열에 복사
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        // 최종 결과 반환
        return answer;
    }
}
