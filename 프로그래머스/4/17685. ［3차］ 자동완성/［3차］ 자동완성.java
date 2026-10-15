import java.util.*;

class Solution {
    
    static class TrieNode {
        
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0; // 해당 노드를 거쳐간 단어의 개수
    }

    public int solution(String[] words) {
        
        TrieNode root = new TrieNode();

        // 1. 모든 단어를 트라이에 삽입하며 count 갱신
        for (String word : words) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
                current.count++;
            }
        }

        int totalTyped = 0;

        // 2. 각 단어별로 최소 입력 글자 수 계산
        for (String word : words) {
            TrieNode current = root;
            int typed = 0;
            
            for (char c : word.toCharArray()) {
                typed++;
                current = current.children.get(c);
                
                // 거쳐간 단어가 1개뿐이라면 이 접두사만으로 유일하게 특정 가능하므로 중단
                if (current.count == 1) {
                    break;
                }
            }
            totalTyped += typed;
        }

        return totalTyped;
    }
}