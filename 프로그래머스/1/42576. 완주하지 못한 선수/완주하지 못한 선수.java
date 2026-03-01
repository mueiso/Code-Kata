import java.util.Arrays;

class Solution {
    
    public String solution(String[] participant, String[] completion) {

        // 두 배열을 정렬
        Arrays.sort(participant);
        Arrays.sort(completion);

        // completion 길이까지만 비교
        for (int i = 0; i < completion.length; i++) {
            // 이름이 다르면 해당 participant가 완주하지 못한 선수
            if (!participant[i].equals(completion[i])) {
                return participant[i];
            }
        }

        // 전부 같다면 마지막 사람이 완주하지 못한 선수
        return participant[participant.length - 1];
    }
}