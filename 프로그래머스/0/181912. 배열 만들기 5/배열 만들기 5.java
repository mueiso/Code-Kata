import java.util.*;

class Solution {
    
    public int[] solution(String[] intStrs, int k, int s, int l) {
        
        List<Integer> list = new ArrayList<>();

        // intStrs 배열의 각 문자열에 대해 반복
        for (String str : intStrs) {
            // s번째 인덱스부터 길이 l만큼 부분 문자열을 추출
            String part = str.substring(s, s + l);

            // 부분 문자열을 정수로 변환
            int num = Integer.parseInt(part);

            // 변환한 숫자가 k보다 크면 결과 리스트에 추가
            if (num > k) {
                list.add(num);
            }
        }

        // 리스트를 int[] 배열로 변환하여 반환
        return list.stream().mapToInt(i -> i).toArray();
    }
}
