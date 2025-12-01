import java.time.DayOfWeek;
import java.time.LocalDate;

class Solution {
    
    public String solution(int a, int b) {
        
        // 2016년 a월 b일 날짜 생성
        LocalDate date = LocalDate.of(2016, a, b);
        
        // 요일 구하기
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        
        // 요일을 int 값으로 변환 (월=1 ~ 일=7)
        int day = dayOfWeek.getValue();

        // 요일 배열 (일요일부터 시작)
        String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        
        // LocalDate 기준은 월=1 ~ 일=7, 금요일=5 -> 배열에서 FRI는 인덱스 5
        // 배열 인덱스 기준: 일=0 ~ 토=6, 따라서 (day % 7) 처리
        return days[day % 7];
    }
}