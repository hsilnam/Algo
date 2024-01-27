import java.util.*;
import java.io.*;


/*
* 1~N의 공장번호를 0~N-1로 맞춘다
1. 각 발전소의 거리를 구하기 위해 2차원 배열로 그래프를 만든다
    - 이미 연결되어있는 것은 거리 0으로, 새로 연결할 것은 거리를 구한다
    - 거리: sqrt(|x2-x1|^2 + |y2-y1|^2)
      이 때, 제한 길이 M을 넘는다면 -1로 만든다 (불가능한 곳)
2. 다익스트라로 최단 거리를 구한다
    - 못가는 길(-1)이면 넘긴다
    - 목표 위치(N-1)에 도달하면 멈춘다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int W = Integer.parseInt(temp[1]);

        double M = Double.parseDouble(br.readLine());

        double[][] positions = new double[N][2];

        for (int i = 0; i < N; i++) { // 발전소는 0부터
            temp = br.readLine().split(" ");
            double x = Double.parseDouble(temp[0]);
            double y = Double.parseDouble(temp[1]);
            positions[i] = new double[]{x, y};
        }

        double[][] graph = new double[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], -1); // 못가는 곳
        }

        for (int i = 0; i < W; i++) {
            temp = br.readLine().split(" ");
            int cur = Integer.parseInt(temp[0]) - 1; // 0번쨰부터 시작하기
            int next = Integer.parseInt(temp[1]) - 1; // 0번째부터 시작하기
            graph[cur][next] = 0;
            graph[next][cur] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 0) {
                    continue;
                }
                double dst = Math.sqrt(Math.pow((positions[i][0] - positions[j][0]), 2) + Math.pow((positions[i][1] - positions[j][1]), 2));
                if (dst > M) {
                    continue;
                }
                graph[i][j] = dst;
                graph[j][i] = dst;
            }
        }

        double[] mins = new double[N];
        Arrays.fill(mins, Double.MAX_VALUE);

        mins[0] = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(0); // num

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == N - 1) {
                break;
            }

            for (int next = 0; next < N; next++) {
                if (cur == next) {
                    continue;
                }
                if (graph[cur][next] == -1) {
                    continue;
                }
                if (mins[next] > mins[cur] + graph[cur][next]) {
                    mins[next] = mins[cur] + graph[cur][next];
                    queue.offer(next);
                }
            }
        }

        int result = (int) Math.floor(mins[N - 1] * 1000);
        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }

}