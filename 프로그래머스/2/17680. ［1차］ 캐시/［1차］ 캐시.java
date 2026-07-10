import java.util.LinkedList;

class Solution {
    
    public int solution(int cacheSize, String[] cities) {
        
        // 코너 케이스: 캐시 크기가 0이면 모두 cache miss 처리
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();
        
        for (String city : cities) {
            // 대소문자를 구분하지 않으므로 모두 소문자로 통일
            String lowerCity = city.toLowerCase();
            
            // 1. Cache Hit 인 경우
            if (cache.contains(lowerCity)) {
                answer += 1;
                // 기존 위치에서 제거하고 가장 최근 위치(맨 뒤)로 갱신
                cache.remove(lowerCity);
                cache.addLast(lowerCity);
            } 
            // 2. Cache Miss 인 경우
            else {
                answer += 5;
                // 캐시가 가득 찼다면 가장 오랫동안 참조되지 않은 맨 앞 원소 삭제
                if (cache.size() >= cacheSize) {
                    cache.removeFirst();
                }
                // 새 도시를 가장 최근 위치(맨 뒤)에 추가
                cache.addLast(lowerCity);
            }
        }
        
        return answer;
    }
}