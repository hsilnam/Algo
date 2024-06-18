import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/*
가장 가까운 공통 조상 구하기
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            // init
            int N = Integer.parseInt(br.readLine());
            ArrayList<Integer>[] tree = new ArrayList[N + 1];
            int[] indegs = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }

            // get input
            for (int i = 0; i < N - 1; i++) {
                String[] temp = br.readLine().split(" ");
                int from = Integer.parseInt(temp[0]);
                int to = Integer.parseInt(temp[1]);
                tree[from].add(to);
                indegs[to]++;
            }

            // get root
            int root = 0;
            for (int i = 1; i <= N; i++) {
                if (indegs[i] == 0) {
                    root = i;
                    break;
                }
            }

            // get parents & levels
            int[] parents = new int[N + 1];
            int[] levels = new int[N + 1];
            parents[root] = -1;

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int p = queue.poll();

                for (int child : tree[p]) {
                    if (p == parents[child]) { // 양방향 그래프인 경우 visited 처리
                        continue;
                    }
                    parents[child] = p;
                    levels[child] = levels[p] + 1;

                    queue.offer(child);
                }
            }

            // get NCA
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            if (levels[a] > levels[b]) {
                int tp = a;
                a = b;
                b = tp;
            }

            while (levels[a] < levels[b]) {
                b = parents[b];
            }

            while (parents[a] != -1 && a != b) {
                a = parents[a];
                b = parents[b];
            }

            result.append(a).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}