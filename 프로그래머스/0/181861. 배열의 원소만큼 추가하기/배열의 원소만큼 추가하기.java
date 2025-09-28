import java.util.*;
import java.util.stream.*;

class Solution {
    
    public int[] solution(int[] arr) {
        
        return Arrays.stream(arr)                            // arr을 스트림으로 변환
                     .flatMap(a -> IntStream.range(0, a)     // a번 반복
                                             .map(i -> a)) 
                     .toArray();                             // 최종적으로 int[]로 반환
    }
}

/*
 [동작 원리]
 1. Arrays.stream(arr)
    배열 → IntStream 생성

 2. flatMap(a -> IntStream.range(0, a).map(i -> a))
    원소 a에 대해 0 ~ a-1 범위 스트림 생성
    각각을 a로 변환 → 총 a개의 a가 생성됨
    flatMap으로 모두 평탄화

 3. toArray()
    최종적으로 int[]로 반환


 [실행 예시]
 arr = [5, 1, 4]

 // 5 → [5, 5, 5, 5, 5]
 // 1 → [1]
 // 4 → [4, 4, 4, 4]

 // 최종: [5, 5, 5, 5, 5, 1, 4, 4, 4, 4]

 */