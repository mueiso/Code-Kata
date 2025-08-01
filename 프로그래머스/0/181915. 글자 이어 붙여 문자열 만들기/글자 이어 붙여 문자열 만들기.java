class Solution {
    
    public String solution(String my_string, int[] index_list) {

        // 결과 문자열을 효율적으로 만들기 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder();

        // index_list의 각 인덱스를 순회하면서 해당 인덱스의 문자를 추출해 StringBuilder에 추가
        for (int idx : index_list)
            sb.append(my_string.charAt(idx)); // my_string의 idx 번째 문자를 sb에 추가

        // 최종적으로 만들어진 문자열을 반환
        return sb.toString();
    }
}
