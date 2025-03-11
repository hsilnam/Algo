import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int temp = 1;
        
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            
            if (ch == '(') {
                stack.push(ch);
                temp *= 2;
            }
            else if (ch == '[') {
                stack.push(ch);
                temp *= 3;
            }
            else if (ch == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                }
                if (input.charAt(i - 1) == '(') {
                    answer += temp;
                }
                stack.pop();
                temp /= 2;
            }
            else if (ch == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                }
                if (input.charAt(i - 1) == '[') {
                    answer += temp;
                }
                stack.pop();
                temp /= 3;
            }
        }
        
        if (!stack.isEmpty()) {
            answer = 0;
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}