import java.io.*;
import java.util.*;


/*
- 단방향 그래프 이용
- 순차적으로 bfs로 검사하면서,
    처음 시작 부분이 제일 앞에있는 노드부터 시작할 수 있도록
    각 노드의 위치에서의 최댓값일때만 값 업데이트
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]) - 1;
            int to = Integer.parseInt(temp[1]) - 1;
            graph[from].add(to);
        }

        int[] cnts = new int[N];

        for (int i = 0; i < N; i++) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{i, 1});
            cnts[i] = Math.max(cnts[i], 1);
            while (!queue.isEmpty()) {
                int[] curInfo = queue.poll();
                int cur = curInfo[0];
                int cnt = curInfo[1];

                int nCnt = cnt + 1;
                for (int next : graph[cur]) {
                    if (cnts[next] < nCnt) {
                        cnts[next] = nCnt;
                        queue.offer(new int[]{next, nCnt});
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int cnt : cnts) {
            result.append(cnt).append(" ");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}
