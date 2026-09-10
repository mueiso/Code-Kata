import java.util.*;
import java.util.regex.*;

class Solution {
    
    private static class Page {
        
        int index;
        String url;
        int basicScore;
        List<String> externalLinks;
        double linkScore = 0.0;

        Page(int index, String url, int basicScore, List<String> externalLinks) {
            this.index = index;
            this.url = url;
            this.basicScore = basicScore;
            this.externalLinks = externalLinks;
        }

        double getMatchingScore() {
            return basicScore + linkScore;
        }
    }

    public int solution(String word, String[] pages) {
        
        String targetWord = word.toLowerCase();
        int n = pages.length;

        Map<String, Page> pageMap = new HashMap<>();
        List<Page> pageList = new ArrayList<>();

        // 정규표현식 패턴
        Pattern urlPattern = Pattern.compile("<meta[^>]*content=\"(https://[^\"]+)\"", Pattern.CASE_INSENSITIVE);
        Pattern linkPattern = Pattern.compile("<a href=\"(https://[^\"]+)\">", Pattern.CASE_INSENSITIVE);

        for (int i = 0; i < n; i++) {
            String pageHtml = pages[i];

            // 1. 자신의 URL 추출
            String url = "";
            Matcher urlMatcher = urlPattern.matcher(pageHtml);
            if (urlMatcher.find()) {
                url = urlMatcher.group(1);
            }

            // 2. 외부 링크 추출
            List<String> links = new ArrayList<>();
            Matcher linkMatcher = linkPattern.matcher(pageHtml);
            while (linkMatcher.find()) {
                links.add(linkMatcher.group(1));
            }

            // 3. 기본 점수 계산 (알파벳 이외의 문자로 단어 분리)
            int basicScore = 0;
            String lowerHtml = pageHtml.toLowerCase();
            String[] tokens = lowerHtml.split("[^a-z]+");
            for (String token : tokens) {
                if (token.equals(targetWord)) {
                    basicScore++;
                }
            }

            Page page = new Page(i, url, basicScore, links);
            pageMap.put(url, page);
            pageList.add(page);
        }

        // 4. 링크 점수 계산
        for (Page page : pageList) {
            if (page.externalLinks.isEmpty()) continue;

            double scorePerLink = (double) page.basicScore / page.externalLinks.size();
            for (String linkUrl : page.externalLinks) {
                if (pageMap.containsKey(linkUrl)) {
                    pageMap.get(linkUrl).linkScore += scorePerLink;
                }
            }
        }

        // 5. 최고 매칭 점수의 페이지 인덱스 찾기
        int bestIndex = 0;
        double maxScore = -1.0;

        for (Page page : pageList) {
            double matchingScore = page.getMatchingScore();
            if (matchingScore > maxScore) {
                maxScore = matchingScore;
                bestIndex = page.index;
            }
        }

        return bestIndex;
    }
}