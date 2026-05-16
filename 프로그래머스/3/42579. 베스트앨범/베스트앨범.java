import java.util.*;

class Solution {
    
    /* 노래 정보를 담을 클래스 */
    static class Song implements Comparable<Song> {
        
        int id;
        int play;

        public Song(int id, int play) {
            
            this.id = id;
            this.play = play;
        }

        // 장르 내 정렬 기준: 재생 횟수 내림차순 -> 고유 번호 오름차순
        @Override
        public int compareTo(Song other) {
            
            if (this.play == other.play) {
                return this.id - other.id;
            }
            
            return other.play - this.play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> genrePlayCount = new HashMap<>();
        Map<String, List<Song>> songsByGenre = new HashMap<>();

        // 1. 데이터 파싱 및 그룹화
        for (int i = 0; i < genres.length; i++) {
            genrePlayCount.put(genres[i], genrePlayCount.getOrDefault(genres[i], 0) + plays[i]);
            
            if (!songsByGenre.containsKey(genres[i])) {
                songsByGenre.put(genres[i], new ArrayList<>());
            }
            songsByGenre.get(genres[i]).add(new Song(i, plays[i]));
        }

        // 2. 총 재생 횟수에 따라 장르 정렬
        List<String> sortedGenres = new ArrayList<>(genrePlayCount.keySet());
        Collections.sort(sortedGenres, (a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));

        // 3. 각 장르 내에서 노래 선정 (최대 2개)
        List<Integer> result = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<Song> songs = songsByGenre.get(genre);
            Collections.sort(songs); // Song 클래스에 정의한 compareTo 기준

            result.add(songs.get(0).id);
            if (songs.size() > 1) {
                result.add(songs.get(1).id);
            }
        }

        // 4. 리스트를 배열로 변환
        return result.stream().mapToInt(i -> i).toArray();
    }
}