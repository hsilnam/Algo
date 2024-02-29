import java.util.*;
import java.io.*;

/*
- bfs 이용한 최단거리 구하기 문제
    - queue에 (좌표,거리)정보를 저장하였다
    - 거리가 짧은 것에 우선순위를 둘 수 있도록 PriorityQueue를 사용하였다
    - tg으로부터 끝까지 뻗어나갔을 때 다른 위치에 얼마나 걸리는지 거리값(dist)을 저장하였다
    - 0인부분은 접근하지 못하니 넘어가게 하였다
- 거리값을 저장하는 배열을 초기화할때,
    입력값이 0(원래 못가는 부분)인 부분은을 제외하고 -1(원래 갈 수 있는데 못가는 부분)로 초기화 해주었다
    -> 원래갈 수 있는데 못가는 부분을 표시해줘야하기 때문이다
        (bfs는 갈 수 있는 부분만 검사하니까)
 */
public class Main {
    static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        int[] tgPos = new int[2];
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
                if (map[i][j] == 2) {
                    tgPos[0] = i;
                    tgPos[1] = j;
                }
            }
        }

        int[][] dists = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dists[i][j] = (map[i][j] == 0) ? 0 : -1;
            }
        }
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        }); // x, y, dist
        queue.offer(new int[]{tgPos[0], tgPos[1], 0});
        visited[tgPos[0]][tgPos[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            dists[cur[0]][cur[1]] = cur[2];

            int nDist = cur[2] + 1;
            for (int[] move : moves) {
                int nx = move[0] + cur[0];
                int ny = move[1] + cur[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    continue;
                }
                queue.offer(new int[]{nx, ny, nDist});
                visited[nx][ny] = true;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result.append(dists[i][j]).append(" ");
            }
            result.append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }
}