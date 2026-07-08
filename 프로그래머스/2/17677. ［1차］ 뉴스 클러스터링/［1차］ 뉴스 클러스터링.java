import java.util.ArrayList;

class Solution {
    
    public int solution(String str1, String str2) {
        
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        // 1. 대소문자 구분을 없애기 위해 미리 소문자로 변환
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 2. str1 다중집합 만들기
        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);

            // 알파벳 소문자 범위 내에 있는 경우만 추가
            if (c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                list1.add("" + c1 + c2);
            }
        }

        // 3. str2 다중집합 만들기
        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);

            if (c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                list2.add("" + c1 + c2);
            }
        }

        // 4. 예외 처리: 둘 다 공집합인 경우 자카드 유사도는 1
        if (list1.isEmpty() && list2.isEmpty()) {
            return 65536;
        }

        // 두 집합의 원래 크기 합산 (합집합 계산용)
        int totalOriginalSize = list1.size() + list2.size();
        int intersection = 0;

        // 5. 교집합 크기 구하기
        for (String s : list1) {
            // list2에 해당 원소가 있으면 하나를 제거하고 교집합 크기 증가
            if (list2.remove(s)) {
                intersection++;
            }
        }

        // 6. 합집합 크기 계산
        int union = totalOriginalSize - intersection;

        // 7. 자카드 유사도 계산 후 65536을 곱한 정수부 반환
        // 정수 나눗셈 방지를 위해 double 캐스팅 필수
        double jaccard = (double) intersection / union;
        
        return (int) (jaccard * 65536);
    }
}