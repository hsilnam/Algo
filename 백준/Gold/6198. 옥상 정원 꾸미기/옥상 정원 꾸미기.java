import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // get input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N + 1];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
        heights[N] = Integer.MAX_VALUE; // 마지막에 제일 큰 수를 두어 모든 계산이 동일하게 맞춤

        long sum = 0;
        Stack<Integer> stack = new Stack<>(); // 건물 idx
        stack.push(0); // 처음 것 스택에 넣음
        for (int i = 1; i <= N; i++) { //
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) { // 현재 높이보다 stack 제일 위에 있는 것이 작다면 반복
                sum += i - stack.pop() - 1;
            }
            stack.push(i); // 현재 idx 넣음
        }
        System.out.println(sum);
    }

}