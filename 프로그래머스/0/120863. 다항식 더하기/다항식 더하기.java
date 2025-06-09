class Solution {
    public String solution(String polynomial) {
        String[] terms = polynomial.split(" \\+ ");
        int xCount = 0;
        int constant = 0;

        for (String term : terms) {
            if (term.contains("x")) {
                String coef = term.replace("x", "");
                xCount += coef.equals("") ? 1 : Integer.parseInt(coef);
            } else {
                constant += Integer.parseInt(term);
            }
        }

        // 결과 조합
        StringBuilder sb = new StringBuilder();
        if (xCount > 0) {
            sb.append(xCount == 1 ? "x" : xCount + "x");
        }
        if (constant > 0) {
            if (xCount > 0) sb.append(" + ");
            sb.append(constant);
        }

        return sb.toString();
    }
}
