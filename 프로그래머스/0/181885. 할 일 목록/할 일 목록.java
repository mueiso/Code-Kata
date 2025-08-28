class Solution {
    
    public String[] solution(String[] todo_list, boolean[] finished) {
        
        // 아직 끝내지 못한 할 일들을 임시로 저장할 문자열
        String str = "";
        
        // finished 배열을 순회하면서
        for(int i = 0; i < finished.length; i++) {

            /*
             * 만약 해당 일이 끝나지 않았다면 (finished[i] == false)
             * str에 todo_list[i]를 추가하고, 뒤에 구분자 ","를 붙인다
             */
            str = finished[i] == false ? str + todo_list[i] + "," : str;
        }

        // 구분자 "," 기준으로 나눠 문자열 배열로 반환
        return str.split(",");
    }
}
