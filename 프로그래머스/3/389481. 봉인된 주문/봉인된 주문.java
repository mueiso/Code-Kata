import java.util.Arrays;

class Solution {
    
    private static final long[] POW26 = new long[13];

    static {
        POW26[0] = 1L;
        for (int i = 1; i <= 12; i++) {
            POW26[i] = POW26[i - 1] * 26L;
        }
    }

    public String solution(long n, String[] bans) {
        
        // 1. bans 배열의 각 문자열을 고유 정수 인덱스로 변환 후 오름차순 정렬
        long[] banIndices = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banIndices[i] = stringToIndex(bans[i]);
        }
        Arrays.sort(banIndices);

        // 2. 삭제된 주문 위치를 반영하여 목표 인덱스 n 보정
        long targetIndex = n;
        for (long banIdx : banIndices) {
            if (banIdx <= targetIndex) {
                targetIndex++;
            } else {
                break;
            }
        }

        // 3. 보정된 정수 인덱스를 원래 문자열로 복원
        return indexToString(targetIndex);
    }

    private long stringToIndex(String s) {
        
        int len = s.length();
        long base = 0;
        for (int i = 1; i < len; i++) {
            base += POW26[i];
        }

        long val = 0;
        for (int i = 0; i < len; i++) {
            val = val * 26 + (s.charAt(i) - 'a');
        }

        return base + val + 1;
    }

    private String indexToString(long index) {
        
        int len = 1;
        while (index > POW26[len]) {
            index -= POW26[len];
            len++;
        }

        long val = index - 1;
        char[] chars = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            chars[i] = (char) ('a' + (val % 26));
            val /= 26;
        }

        return new String(chars);
    }
}