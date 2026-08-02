import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    // 단어와 현재까지의 변환 횟수를 저장하는 클래스
    static class Node {
        String word;
        int count;

        Node(String word, int count) {
            
            this.word = word;
            this.count = count;
        }
    }

    public int solution(String begin, String target, String[] words) {
        
        // 1. target이 words에 포함되어 있는지 확인
        boolean hasTarget = false;
        for (String word : words) {
            if (word.equals(target)) {
                hasTarget = true;
                break;
            }
        }
        if (!hasTarget) return 0;

        // 2. BFS 탐색 준비
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        queue.offer(new Node(begin, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // target 단어에 도달하면 변환 횟수 반환
            if (current.word.equals(target)) {
                return current.count;
            }

            for (int i = 0; i < words.length; i++) {
                // 아직 방문하지 않았고, 알파벳이 딱 1개만 다른 단어인 경우
                if (!visited[i] && canConvert(current.word, words[i])) {
                    visited[i] = true; // 방문 처리
                    queue.offer(new Node(words[i], current.count + 1));
                }
            }
        }

        return 0;
    }

    /* 두 단어가 한 글자만 다른지 확인하는 함수 */
    private boolean canConvert(String w1, String w2) {
        
        int diffCount = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) return false; // 2개 이상 다르면 바로 false
        }
        
        return diffCount == 1;
    }
}