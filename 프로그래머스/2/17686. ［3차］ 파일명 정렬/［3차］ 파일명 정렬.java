import java.util.Arrays;
import java.util.Comparator;

class Solution {
    
    public String[] solution(String[] files) {
        
        // 자바의 Arrays.sort와 커스텀 Comparator를 이용해 정렬합니다.
        Arrays.sort(files, new Comparator<String>() {
            
            @Override
            public int compare(String f1, String f2) {
                
                // 1. f1 파일명에서 HEAD와 NUMBER 분리하기
                int idx1 = 0;
                // 숫자가 아닐 때까지 전진 -> HEAD의 끝을 찾음
                while (idx1 < f1.length() && !Character.isDigit(f1.charAt(idx1))) {
                    idx1++;
                }
                String head1 = f1.substring(0, idx1).toLowerCase(); // 대소문자 무시를 위해 소문자화
                
                int numStart1 = idx1;
                // 숫자이고, 최대 5글자까지만 NUMBER로 인정
                while (idx1 < f1.length() && Character.isDigit(f1.charAt(idx1)) && (idx1 - numStart1) < 5) {
                    idx1++;
                }
                int num1 = Integer.parseInt(f1.substring(numStart1, idx1)); // 앞의 0을 지우기 위해 int 변환


                // 2. f2 파일명에서 HEAD와 NUMBER 분리하기
                int idx2 = 0;
                while (idx2 < f2.length() && !Character.isDigit(f2.charAt(idx2))) {
                    idx2++;
                }
                String head2 = f2.substring(0, idx2).toLowerCase();
                
                int numStart2 = idx2;
                while (idx2 < f2.length() && Character.isDigit(f2.charAt(idx2)) && (idx2 - numStart2) < 5) {
                    idx2++;
                }
                int num2 = Integer.parseInt(f2.substring(numStart2, idx2));


                // 3. 정렬 기준에 맞춰 비교 수행
                // 우선 HEAD를 기준으로 사전 순 정렬
                int headCompare = head1.compareTo(head2);
                if (headCompare != 0) {
                    return headCompare;
                }
                
                // HEAD가 같다면 NUMBER를 기준으로 숫자 크기 정렬
                return Integer.compare(num1, num2);
                // 둘 다 같다면 0을 반환하여 기존 순서(Stable)를 유지합니다.
            }
        });
        
        return files;
    }
}