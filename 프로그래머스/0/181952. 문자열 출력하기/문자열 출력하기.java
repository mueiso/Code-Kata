import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int a = str.length();

        if(a >= 1 && a <= 1000000) {
            System.out.println(str);
        }
    }
}