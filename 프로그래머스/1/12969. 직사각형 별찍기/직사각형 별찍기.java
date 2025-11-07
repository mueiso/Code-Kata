import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt(); // 가로 길이
        int m = scanner.nextInt(); // 세로 길이
        
        scanner.close(); // 입력 종료
        
        // 세로(m) 줄 반복
        for (int i = 0; i < m; i++) {
            // 가로(n) 개의 '*' 출력
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            System.out.println(); // 줄바꿈
        }
    }
}
