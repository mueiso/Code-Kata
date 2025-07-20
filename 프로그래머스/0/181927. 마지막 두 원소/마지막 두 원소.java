class Solution {
    
    public int[] solution(int[] num_list) {
        
        int length = num_list.length;           // ① 배열 길이 구하기
        int lastNum = num_list[length - 1];     // ② 마지막 원소
        int prevNum = num_list[length - 2];     // ③ 그전 원소

        int addValue = (lastNum > prevNum) ? (lastNum - prevNum) : (lastNum * 2);   // ④ 조건에 따라 추가할 값 결정

        int[] answer = new int[length + 1];     // ⑤ 새 배열 생성 (기존 길이 + 1)
        
        for (int i = 0; i < length; i++) {      // ⑥ 기존 배열 복사
            answer[i] = num_list[i];
        }
        
        answer[length] = addValue;              // ⑦ 마지막에 계산한 값 추가

        return answer;                          // ⑧ 결과 반환
    }
}

/*
 ① 입력으로 받은 배열 num_list의 길이를 length에 저장
 ② 배열의 마지막 원소 가져오기
 ③ 배열의 마지막에서 두 번째 원소 가져오기
 ④ 문제 조건 맞춰서 계산(lastNum > prevNum이면 lastNum - prevNum, 아니면 lastNum * 2)
 ⑤ 새로운 값 추가하기 위해 기존 배열보다 한 칸 더 큰 배열 생성
 ⑥ 기존 배열의 내용을 새로운 배열 answer에 복사(기존 값 유지하면서 마지막에 새 값 덧붙이기 위해)
 ⑦ 앞에서 계산한 추가 값을 마지막 인덱스에 저장
 ⑧ 최종적으로 새로운 배열 반환
 */