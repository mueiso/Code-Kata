import java.util.Arrays;

class Solution {
    
    public boolean solution(String[] phone_book) {
        
        // 1. 전화번호부를 사전순으로 정렬
        Arrays.sort(phone_book);

        // 2. 인접한 두 번호만 비교 (정렬되어 있으므로 접두어라면 반드시 붙어 있음)
        for (int i = 0; i < phone_book.length - 1; i++) {
            // 뒷 번호가 앞 번호로 시작하는지 확인
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        // 3. 반복문이 끝날 때까지 발견되지 않으면 true
        return true;
    }
}