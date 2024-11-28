import java.io.*;
import java.util.*;

/*
양쪽에 max, min 우선순위 큐를 두어서 무조건 가운데의 왼쪽을 참고하도록 만든다
- 가운데 값이 어야하기 때문에 양쪽의 밸런스를 맞추기 위해 크기가 같거나 maxPQ가 더 적어야한다
    (maxPQ에 우선적으로 쌓여서 왼쪽 거를 항상 참고해서 가져올 수 있게)
- 만약 maxPQ의 최대값보다 minPQ의 최소값이 작다면 swap한다

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(br.readLine());
        Queue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> minPQ = new PriorityQueue<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (maxPQ.size() <= minPQ.size()) {
                maxPQ.offer(num);
            } else {
                minPQ.offer(num);
            }

            if (!maxPQ.isEmpty() && !minPQ.isEmpty() &&
                    maxPQ.peek() > minPQ.peek()) {
                int temp = minPQ.poll();
                minPQ.offer(maxPQ.poll());
                maxPQ.offer(temp);
            }
            answer.append(maxPQ.peek()).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}