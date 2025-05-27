import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int index1 = 0;
        int index2 = 0;

        for (String word : goal) {
            // cards1에서 단어를 사용할 수 있는 경우
            if (index1 < cards1.length && cards1[index1].equals(word)) {
                index1++;
            }
            // cards2에서 단어를 사용할 수 있는 경우
            else if (index2 < cards2.length && cards2[index2].equals(word)) {
                index2++;
            }
            // 어떤 카드 뭉치에서도 현재 단어를 찾을 수 없는 경우
            else {
                return "No";
            }
        }

        return "Yes";
    }
}