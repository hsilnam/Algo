import java.io.*;
import java.util.*;

/*
스택: 배열로 실제로 구현해보기
- top이란 index를 이용해서 현재 stack의 최신 위치를 저장한다
- LIFO
 */

public class Main {
    public static class ArrayStack {
        private int top = -1;
        private int[] stack = new int[10_001];

        public void push(int x) {
            stack[++top] = x;
        }

        public int pop() {
            return stack[top--];
        }

        public int size() {
            return top + 1;
        }

        public boolean isEmpty() {
            return (top == -1) ? true : false;
        }

        public int peek() {
            return stack[top];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        ArrayStack stack = new ArrayStack();
        for (int n = 0; n < N; n++) {
            String[] temp = br.readLine().split(" ");
            String cmd = temp[0];

            switch (cmd) {
                case "push":
                    stack.push(Integer.parseInt(temp[1]));
                    break;
                case "pop":
                    result.append((stack.isEmpty()) ? -1 : stack.pop()).append("\n");
                    break;
                case "size":
                    result.append(stack.size()).append("\n");
                    break;
                case "empty":
                    result.append((stack.isEmpty()) ? 1 : 0).append("\n");
                    break;
                case "top":
                    result.append((stack.isEmpty()) ? -1 : stack.peek()).append("\n");
                    break;
            }
        }

        bw.write(result.toString());
        br.close();
        bw.close();
    }
}