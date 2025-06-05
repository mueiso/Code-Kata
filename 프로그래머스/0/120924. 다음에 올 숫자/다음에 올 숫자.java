class Solution {
    public int solution(int[] common) {
        // 등차수열 여부 판별을 위해 첫 두 차를 계산
        int diff = common[1] - common[0];
        // 세 번째와 두 번째의 차가 같으면 등차수열
        if (common[2] - common[1] == diff) {
            // 마지막 원소에 공차를 더하여 다음 항 반환
            return common[common.length - 1] + diff;
        } else {
            // 등비수열인 경우 공비를 계산 (정수로 나눠진다고 보장됨)
            int ratio = common[1] / common[0];
            // 마지막 원소에 공비를 곱하여 다음 항 반환
            return common[common.length - 1] * ratio;
        }
    }
}
