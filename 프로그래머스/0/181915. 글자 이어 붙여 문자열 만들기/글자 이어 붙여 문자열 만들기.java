class Solution {
    
    public String solution(String my_string, int[] index_list) {
        
        // StringBuilder는 문자열을 효율적으로 이어 붙이기 위해 사용
        StringBuilder sb = new StringBuilder();

        // index_list의 각 인덱스를 순회하면서 해당하는 문자를 sb에 추가
        for (int index : index_list) {
            sb.append(my_string.charAt(index));
        }

        // 최종 문자열 반환
        return sb.toString();
    }
}
