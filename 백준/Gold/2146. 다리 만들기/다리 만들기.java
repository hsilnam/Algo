import java.util.*;
import java.io.*;


public class Main {
    static class Edge {
        int x, y, depth;

        public Edge(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    '}';
        }
    }

    static int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int min = 100 * 100;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                Queue<int[]> queue = new ArrayDeque();
                queue.offer(new int[]{i, j});

                boolean[][] edgeVisited = new boolean[N][N];
                Queue<Edge> edgeQueue = new ArrayDeque<>();
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();

                    int zeroCnt = 0;
                    for (int[] move : moves) {
                        int nx = move[0] + cur[0];
                        int ny = move[1] + cur[1];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }
                        if (visited[nx][ny]) {
                            continue;
                        }
                        if (map[nx][ny] == 0) {
                            zeroCnt += 1;
                            continue;
                        }
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                    if (zeroCnt >= 1) {
                        edgeQueue.offer(new Edge(cur[0], cur[1], 0));
                    }
                    edgeVisited[cur[0]][cur[1]] = true;
                }

                // 가장자리들을 넓혀가면서 최단거리 구하기
                boolean findMin = false;
                while (!edgeQueue.isEmpty()) {
                    if (findMin) {
                        break;
                    }
                    Edge cur = edgeQueue.poll();

                    int ndepth = cur.depth + 1;
                    for (int[] move : moves) {
                        int nx = move[0] + cur.x;
                        int ny = move[1] + cur.y;
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }
                        if (edgeVisited[nx][ny]) {
                            continue;
                        }
                        if (map[nx][ny] == 1) {
                            findMin = true;
                            min = Math.min(min, cur.depth);
                            break;
                        }
                        edgeQueue.offer(new Edge(nx, ny, ndepth));
                        edgeVisited[nx][ny] = true;
                    }
                }
            }
        }

        System.out.println(min);
    }
}