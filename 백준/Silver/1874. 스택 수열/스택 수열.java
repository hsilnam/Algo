import java.util.*;
import java.io.*;

/*
- stack과 visited(boolean[]) 활용
- push(+): peek한 숫자부터 순차적으로 올리는 숫자가 주어진 숫자와 N 이하일 때까지, 처음 방문하는 숫자면 stack에 넣어준다
- pop(-): peek한 값이 주어진 숫자와 똑같으면 stack에서 빼내준다
    - 계산을 편하게 하기 위해 처음에 stack에 0을 넣어준다
- 최종 연산 후 stack 사이즈가 1초과이면(0을 포함하기 때문에) 'NO'를 출력한다

 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[N + 1];
        stack.push(0);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            int temp = stack.peek();
            while (temp < num && temp < N) {
                temp++;
                if (!visited[temp]) {
                    stack.push(temp);
                    result.append("+").append("\n");
                    visited[temp] = true;
                }
            }
            if (stack.peek() == num) {
                stack.pop();
                result.append("-").append("\n");
            }
        }

        bw.write((stack.size() > 1) ? "NO" : result.toString());

        br.close();
        bw.close();
    }
}