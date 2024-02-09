import java.util.*;
import java.io.*;

/*
- 증가하는 경우밖에 없기 때문에 max값을 10^9로 설정한다 (A,B가 최대 10^9)
- bfs, priorityQueue 이용
    - long으로 값을 만들어서 검사
        case1: 2*10^9 < Integer.MAX_VALUE
        case2: 10^9 * 10 + 1 > Integer.MAX_VALUE
        => case2에서 overflow 문제가 될 가능성이 있기 때문에

 */
public class Main {
    public static final int maxSize = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        int A = Integer.parseInt(temp[0]);
        int B = Integer.parseInt(temp[1]);

        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        queue.offer(new int[]{A, 1});
        boolean[] visited = new boolean[maxSize + 1];
        visited[A] = true;
        int result = -1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == B) {
                result = cur[1];
                break;
            }

            if ((long) cur[0] * 2L <= maxSize) {
                queue.offer(new int[]{cur[0] * 2, cur[1] + 1});
                visited[cur[0] * 2] = true;
            }
            if ((long) cur[0] * 10L + 1L <= maxSize) {
                queue.offer(new int[]{cur[0] * 10 + 1, cur[1] + 1});
                visited[cur[0] * 10 + 1] = true;
            }
        }

        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }
}