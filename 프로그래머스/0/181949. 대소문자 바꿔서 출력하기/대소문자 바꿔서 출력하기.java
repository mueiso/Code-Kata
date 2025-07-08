import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String answer = "";

        //Stack <Character> stack = new Stack <> ();

        for(Character c : a.toCharArray()){
            if(Character.isUpperCase(c)){
                //stack.push(Character.toLowerCase(c));
                answer += Character.toLowerCase(c);
            }
            else if(Character.isLowerCase(c)){
                //stack.push(Character.toUpperCase(c));
                answer += Character.toUpperCase(c);
            }
        } 
        System.out.println(answer);
    }
}