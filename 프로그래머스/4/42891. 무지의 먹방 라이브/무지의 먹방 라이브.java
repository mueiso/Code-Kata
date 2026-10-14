import java.util.*;

class Solution {
    
    static class Food implements Comparable<Food> {
        
        int index;
        long time;

        Food(int index, long time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Food o) {
            return Long.compare(this.time, o.time);
        }
    }

    public int solution(int[] food_times, long k) {
        
        long totalTime = 0;
        List<Food> foods = new ArrayList<>();
        
        for (int i = 0; i < food_times.length; i++) {
            totalTime += food_times[i];
            foods.add(new Food(i + 1, food_times[i]));
        }

        // 전체 먹는 시간이 k보다 작거나 같으면 다 먹을 수 있으므로 -1 리턴
        if (totalTime <= k) return -1;

        // 소요 시간이 짧은 음식부터 정렬
        Collections.sort(foods);

        long preTime = 0; // 직전에 다 먹은 음식의 소요 시간
        long n = food_times.length; // 남은 음식의 개수
        int idx = 0;

        for (Food f : foods) {
            long diff = f.time - preTime;
            if (diff != 0) {
                long spend = diff * n;
                if (k >= spend) {
                    k -= spend;
                    preTime = f.time;
                } else {
                    break;
                }
            }
            n--;
            idx++;
        }

        // 남은 음식들을 원래 음식 번호(인덱스) 순으로 재정렬
        List<Food> subList = new ArrayList<>(foods.subList(idx, foods.size()));
        subList.sort(Comparator.comparingInt(a -> a.index));

        // 남은 시간 k를 남은 음식 개수로 나눈 나머지에 해당하는 음식을 선택
        return subList.get((int) (k % subList.size())).index;
    }
}