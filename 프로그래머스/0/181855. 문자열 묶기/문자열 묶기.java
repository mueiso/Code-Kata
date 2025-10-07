/* [문제풀이]

 * 카운팅 배열 준비
   문자열 길이는 1 이상 30 이하라고 가정할 수 있으므로, 크기가 31인 배열 lengArr를 만듦
   인덱스 = 문자열 길이, 값 = 해당 길이 문자열 개수

 * 빈도 집계
   주어진 문자열 배열을 순회하면서 각 문자열 길이를 인덱스로 하는 lengArr 값을 1씩 증가
   예: ["a", "bc", "de", "fgh"] → lengArr[1]=1, lengArr[2]=2, lengArr[3]=1

 * 최댓값 탐색
   for문으로 lengArr 배열을 순회하면서 가장 큰 값을 찾아 answer에 저장
   이 값이 곧 가장 많은 그룹의 크기

 * 결과 반환
   최댓값 answer를 반환
   
 */

class Solution {
    
    public int solution(String[] strArr) {
        
        int answer = 0;

        // 문자열 길이가 1~30까지 가능하므로 카운트 배열을 준비
        int[] lengArr = new int[31];

        // 각 문자열의 길이를 인덱스로 하여 개수 증가
        for (int i = 0; i < strArr.length; i++) {
            lengArr[strArr[i].length()]++;
        }

        // 가장 큰 그룹의 크기 찾기
        for (int i = 0; i <= 30; i++) {
            answer = Math.max(answer, lengArr[i]);
        }

        return answer;
    }
}