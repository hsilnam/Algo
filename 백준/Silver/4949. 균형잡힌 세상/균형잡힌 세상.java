import java.util.*;
import java.io.*;

/*
- 스택, 문자열 문제
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder result = new StringBuilder();
        while (true) {
            String temp = br.readLine();
            if(temp.equals(".")) {
                break;
            }
            Stack<Character> stack = new Stack<>();
            boolean isOk = true;
            for (char c : temp.toCharArray()) {
                if (!isOk && c != '.') {
                    continue;
                }

                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == '.') {
                    result.append((stack.isEmpty() && isOk) ? "yes" : "no").append("\n");
                } else if (c == ')' || c == ']') {
                    if (stack.isEmpty()) {
                        isOk = false;
                        continue;
                    }
                    if ((c == ')' && stack.peek() == '(') ||
                            (c == ']' && stack.peek() == '[')) {
                        stack.pop();
                    } else {
                        isOk = false;
                    }

                }
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }

}