import java.util.*;

class Solution {
    
    private String sentence;
    private int N;
    private int[] count;
    private int[] firstPos;
    private int[] lastPos;
    private String[] memo;

    public String solution(String sentence) {
        
        this.sentence = sentence;
        this.N = sentence.length();
        this.count = new int[26];
        this.firstPos = new int[26];
        this.lastPos = new int[26];
        Arrays.fill(firstPos, -1);
        Arrays.fill(lastPos, -1);

        // 1. 소문자 카운트 및 위치 집계
        for (int i = 0; i < N; i++) {
            char ch = sentence.charAt(i);
            if (Character.isLowerCase(ch)) {
                int cIdx = ch - 'a';
                count[cIdx]++;
                if (firstPos[cIdx] == -1) {
                    firstPos[cIdx] = i;
                }
                lastPos[cIdx] = i;
            }
        }

        this.memo = new String[N + 1];
        String result = dfs(0);

        if (result.equals("invalid")) {
            return "invalid";
        }
        return result;
    }

    private String dfs(int i) {
        
        if (i == N) {
            return "";
        }
        if (memo[i] != null) {
            return memo[i];
        }

        // 선택지 1: 시작 문자가 소문자인 경우 (Type 2 또는 Type 3)
        if (Character.isLowerCase(sentence.charAt(i))) {
            char c2 = sentence.charAt(i);
            int c2Idx = c2 - 'a';
            if (count[c2Idx] == 2) {
                int j = lastPos[c2Idx];
                if (j > i) {
                    String w = getWord(i, j);
                    if (w != null) {
                        String res = dfs(j + 1);
                        if (!res.equals("invalid")) {
                            String ans = w + (res.isEmpty() ? "" : " " + res);
                            memo[i] = ans;
                            return ans;
                        }
                    }
                }
            }
        } else {
            // 선택지 2: 시작 문자가 대문자인 경우

            // Option 2A: Type 1 시도 (바로 다음 문자가 소문자인 경우)
            if (i + 1 < N && Character.isLowerCase(sentence.charAt(i + 1))) {
                char c1 = sentence.charAt(i + 1);
                int c1Idx = c1 - 'a';
                int j = lastPos[c1Idx] + 1;
                if (j < N) {
                    String w = getWord(i, j);
                    if (w != null) {
                        String res = dfs(j + 1);
                        if (!res.equals("invalid")) {
                            String ans = w + (res.isEmpty() ? "" : " " + res);
                            memo[i] = ans;
                            return ans;
                        }
                    }
                }
            }

            // Option 2B: Type 0 시도 (순수 대문자 접두사)
            int k = i;
            while (k < N && Character.isUpperCase(sentence.charAt(k))) {
                k++;
            }
            for (int j = k - 1; j >= i; j--) {
                String w = getWord(i, j);
                if (w != null) {
                    String res = dfs(j + 1);
                    if (!res.equals("invalid")) {
                        String ans = w + (res.isEmpty() ? "" : " " + res);
                        memo[i] = ans;
                        return ans;
                    }
                }
            }
        }

        memo[i] = "invalid";
        return "invalid";
    }

    /* 구간 [i..j]가 올바른 단어 블록인지 검증 및 원본 단어 추출 */
    private String getWord(int i, int j) {
        
        int L = j - i + 1;
        if (L <= 0) return null;

        // Condition 0: 구간 [i..j] 내부의 모든 소문자가 전체 문장에서 완전히 이 구간에만 존재하는지 검사
        for (int idx = i; idx <= j; idx++) {
            char ch = sentence.charAt(idx);
            if (Character.isLowerCase(ch)) {
                int cIdx = ch - 'a';
                if (firstPos[cIdx] < i || lastPos[cIdx] > j) {
                    return null;
                }
            }
        }

        char startChar = sentence.charAt(i);

        if (Character.isUpperCase(startChar)) {
            // Type 0 검사 (순수 대문자)
            boolean allUpper = true;
            for (int idx = i; idx <= j; idx++) {
                if (!Character.isUpperCase(sentence.charAt(idx))) {
                    allUpper = false;
                    break;
                }
            }
            if (allUpper) {
                return sentence.substring(i, j + 1);
            }

            // Type 1 검사 (U1 c1 U2 c1 ... Uk)
            if (L >= 3 && L % 2 == 1) {
                if (Character.isUpperCase(sentence.charAt(j))) {
                    char c1 = sentence.charAt(i + 1);
                    if (Character.isLowerCase(c1)) {
                        int c1Idx = c1 - 'a';
                        if (count[c1Idx] == (L - 1) / 2) {
                            StringBuilder sb = new StringBuilder();
                            boolean match = true;
                            for (int p = 0; p < L; p++) {
                                char curr = sentence.charAt(i + p);
                                if (p % 2 == 0) {
                                    if (!Character.isUpperCase(curr)) {
                                        match = false;
                                        break;
                                    }
                                    sb.append(curr);
                                } else {
                                    if (curr != c1) {
                                        match = false;
                                        break;
                                    }
                                }
                            }
                            if (match) {
                                return sb.toString();
                            }
                        }
                    }
                }
            }
            return null;

        } else {
            // Type 2 또는 Type 3 검사
            char c2 = startChar;
            int c2Idx = c2 - 'a';
            if (sentence.charAt(j) != c2 || count[c2Idx] != 2) {
                return null;
            }

            // Type 2 검사 (c2 U1 U2 ... Uk c2)
            if (j - i - 1 >= 1) {
                boolean innerAllUpper = true;
                for (int idx = i + 1; idx <= j - 1; idx++) {
                    if (!Character.isUpperCase(sentence.charAt(idx))) {
                        innerAllUpper = false;
                        break;
                    }
                }
                if (innerAllUpper) {
                    return sentence.substring(i + 1, j);
                }
            }

            // Type 3 검사 (c2 U1 c1 U2 ... Uk c2)
            int innerL = j - i - 1;
            if (innerL >= 3 && innerL % 2 == 1) {
                if (Character.isUpperCase(sentence.charAt(i + 1)) && Character.isUpperCase(sentence.charAt(j - 1))) {
                    char c1 = sentence.charAt(i + 2);
                    if (Character.isLowerCase(c1) && c1 != c2) {
                        int c1Idx = c1 - 'a';
                        if (count[c1Idx] == (innerL - 1) / 2) {
                            if (firstPos[c1Idx] >= i + 1 && lastPos[c1Idx] <= j - 1) {
                                StringBuilder sb = new StringBuilder();
                                boolean match = true;
                                for (int p = 0; p < innerL; p++) {
                                    char curr = sentence.charAt(i + 1 + p);
                                    if (p % 2 == 0) {
                                        if (!Character.isUpperCase(curr)) {
                                            match = false;
                                            break;
                                        }
                                        sb.append(curr);
                                    } else {
                                        if (curr != c1) {
                                            match = false;
                                            break;
                                        }
                                    }
                                }
                                if (match) {
                                    return sb.toString();
                                }
                            }
                        }
                    }
                }
            }

            return null;
        }
    }
}