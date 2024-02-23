import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            String cmd = temp[0];
            if (cmd.equals("push")) {
                stack.add(Integer.parseInt(temp[1]));
            } else if (cmd.equals("pop")) {
                result.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
            } else if (cmd.equals("size")) {
                result.append(stack.size()).append("\n");
            } else if (cmd.equals("empty")) {
                result.append(stack.isEmpty() ? 1 : 0).append("\n");
            } else if (cmd.equals("top")) {
                result.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}