import java.io.*;
import java.util.*;

/*
입력
- N: 수빈 위치 (0<=N<=100_000)
- K: 동생 위치 (0<=K<=100_000)

조건
- 걷거나 순간이동 가능
    - 걷기: X-1, X+1
    - 순간이동: 2*X

풀이
- bfs 사용

출력
- 수빈이가 동생을 찾을 수 있는 가장 빠른 시간

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[100_001];
        queue.offer(new int[]{N, 0});
        visited[N] = true;

        int answer = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == K) {
                answer = cur[1];
                break;
            }

            for (int i = 0; i < 3; i++) {
                int next = cur[0];
                switch (i) {
                    case 0:
                        next += 1;
                        break;
                    case 1:
                        next -= 1;
                        break;
                    case 2:
                        next *= 2;
                        break;
                }

                if (next < 0 || next >= 100_001) {
                    continue;
                }
                if (visited[next]) {
                    continue;
                }
                queue.offer(new int[]{next, cur[1] + 1});
                visited[next] = true;
            }
        }

        bw.write(Integer.toString(answer));

        br.close();
        bw.flush();
        bw.close();
    }
}