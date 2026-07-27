class Solution {
    
    public int solution(String skill, String[] skill_trees) {
        
        int answer = 0;

        for (String skillTree : skill_trees) {
            // skill에 포함되지 않은 모든 스킬(문자)을 제거
            String filtered = skillTree.replaceAll("[^" + skill + "]", "");

            // 남은 스킬트리가 skill의 맨 처음부터 시작하는 문자열(Prefix)인지 확인
            if (skill.indexOf(filtered) == 0) {
                answer++;
            }
        }

        return answer;
    }
}