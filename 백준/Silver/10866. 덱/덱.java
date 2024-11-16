import java.io.*;
import java.util.*;

/*
deque를 통한 명령어 처리
- switch로 명령어 분기
- remove, peek 연산에서 deque가 비어있을 시 -1를 대신 출력,
    에러나지 않도록 비어있는지 검사
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> deque = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            String ctr = temp[0];

            switch (ctr) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(temp[1]));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(temp[1]));
                    break;
                case "pop_front":
                    answer.append((deque.isEmpty()) ? -1 : deque.removeFirst()).append("\n");
                    break;
                case "pop_back":
                    answer.append((deque.isEmpty()) ? -1 : deque.removeLast()).append("\n");
                    break;
                case "size":
                    answer.append(deque.size()).append("\n");
                    break;
                case "empty":
                    answer.append((deque.isEmpty()) ? 1 : 0).append("\n");
                    break;
                case "front":
                    answer.append((deque.isEmpty()) ? -1 : deque.peekFirst()).append("\n");
                    break;
                case "back":
                    answer.append((deque.isEmpty()) ? -1 : deque.peekLast()).append("\n");
                    break;
            }
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}