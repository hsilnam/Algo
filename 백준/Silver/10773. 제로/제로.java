import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0 && !stack.isEmpty()) {
                stack.pop();
                continue;
            }
            stack.push(input);
        }

        int result = 0;
        for (int ele : stack) {
            result += ele;
        }

        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }
}