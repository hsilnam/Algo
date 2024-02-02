import java.util.*;
import java.io.*;

/*
1. 입력 문자를 순차적으로 stack에 쌓으면서 검사한다
    - stack의 크기 >= 폭발문자 길이
        stack 마지막 폭발문자 길이만큼의 문자열과 문자폭발 문자를 비교했을 시,
        - 동일하면, 빼낸다 (뒤에서부터 검사했을 시 하나라도 다르면 해당 idx 문자부터 다시 stack에 넣는다)
        - 아니라면, 넘긴다
    - stack의 크기 < 폭발문자 길이: stack에 쌓는다
2. 입력 문자를 전부 다 검사했을 경우
    - stack에 남아있는 모든 문자열을 붙여서 출력한다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] inputs = br.readLine().toCharArray();
        char[] boom = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : inputs) {
            stack.push(c);
            if (stack.size() < boom.length) {
                continue;
            }

            // 방법1: stack get, pop 이용
            boolean isBoom = true;
            for (int i = 0; i < boom.length; i++) {
                if (stack.get(stack.size() - (boom.length - i)) != boom[i]) {
                    isBoom = false;
                    break;
                }
            }

            if (isBoom) {
                for (int i = 0; i < boom.length; i++) {
                    stack.pop();
                }
            }

            /*
            // 방법2: stack pop, push 이용
            int boomLastIdx = boom.length - 1;
            if (stack.peek() != boom[boomLastIdx]) {
                continue;
            }

            int idx = -1;
            for (int i = boomLastIdx; i >= 0; i--) {
                if (stack.peek() != boom[i]) {
                    idx = i + 1;
                    break;
                }
                stack.pop();
            }
            if (idx > 0) {
                for (int i = idx; i < boom.length; i++) {
                    stack.push(boom[i]);
                }
            }
            */
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        bw.write((result.length() > 0) ? result.toString() : "FRULA");

        br.close();
        bw.close();
    }
}