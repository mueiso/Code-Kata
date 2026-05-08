class Solution {

    public String solution(String new_id) {

        // 7단계를 순차적으로 수행
        return new KakaoId(new_id)
                .toLowerCase()
                .filter()
                .compressDot()
                .removeDot()
                .fillEmpty()
                .truncate()
                .extend()
                .get();
    }

    private static class KakaoId {

        private String value;

        public KakaoId(String value) {
            
            this.value = value;
        }

        // 1단계: 대문자 -> 소문자
        public KakaoId toLowerCase() {
            
            value = value.toLowerCase();
            
            return this;
        }

        // 2단계: 허용 문자 제외 제거
        public KakaoId filter() {
            
            value = value.replaceAll("[^a-z0-9._-]", "");
            
            return this;
        }

        // 3단계: 연속된 '.' -> '.'
        public KakaoId compressDot() {
            
            value = value.replaceAll("\\.{2,}", ".");
            
            return this;
        }

        // 4단계: 처음과 끝의 '.' 제거
        public KakaoId removeDot() {
            
            value = value.replaceAll("^\\.|\\.$", "");
            
            return this;
        }

        // 5단계: 빈 문자열이면 "a"
        public KakaoId fillEmpty() {

            if (value.isEmpty()) {
                value = "a";
            }

            return this;
        }

        // 6단계: 길이 15 제한 + 끝 '.' 제거
        public KakaoId truncate() {

            if (value.length() >= 16) {
                value = value.substring(0, 15);
            }

            value = value.replaceAll("\\.$", "");

            return this;
        }

        // 7단계: 길이가 3이 될 때까지 마지막 문자 추가
        public KakaoId extend() {

            StringBuilder sb = new StringBuilder(value);

            while (sb.length() < 3) {
                sb.append(sb.charAt(sb.length() - 1));
            }

            value = sb.toString();

            return this;
        }

        // 최종 결과 반환
        public String get() {
            
            return value;
        }
    }
}