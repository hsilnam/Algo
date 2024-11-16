import java.io.*;
import java.util.*;

/*
균형잡힌 괄호
- 괄호(()[])들이 Stack을 통해 서로의 쌍을 남는 것 없이 매칭 시켜주면 됨
- stack에 추가하는 조건: ([이 들어올 경우
- stack에서 삭제하는 조건: 현재 비교하는 값이 stack의 마지막 값의 페어와 일치하는 경우
- 에러 조건: 빈 stack에 )]이 들어오거나 )]의 쌍이 안맞을 경우
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        StringBuilder answer = new StringBuilder();
        while ((input = br.readLine()) != null) {
            if(input.equals(".")) {
                break;
            }
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (!(c == '(' || c == ')' || c == '[' || c == ']')) {
                    continue;
                }

                if (c == '(' || c == '[') {
                    stack.push(c);
                }

                if (!stack.isEmpty()
                        && ((stack.peek() == '(' && c == ')') || (stack.peek() == '[' && c == ']'))) {
                    stack.pop();
                    continue;
                }

                if (stack.isEmpty()
                        && (c == ')' || c == ']')) {
                    stack.push('e'); //err
                    break;
                }
                if (!stack.isEmpty()
                        && ((stack.peek() == '(' && c == ']') || (stack.peek() == '[' && c == ')'))) {
                    stack.push('e'); //err
                    break;
                }
            }

            answer.append((stack.isEmpty()) ? "yes" : "no").append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}