import java.util.*;
import java.io.*;

// 1:익은 사과 / 0: 안익은 사과 / -1: 막힘
public class Main {
    static int[][] moves = {{0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}};
    static int N, M, H;
    static int[][][] map;
    static boolean[][][] visited;
    static int max = -1;
    static int zeroCnt = 0;
    static int changeCnt = 0;

    static class Node {
        int x;
        int y;
        int h;
        int depth;

        public Node(int x, int y, int h, int depth) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws Exception {
        // 1. input 받기 + 안익은 사과 개수 구하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[1]);
        M = Integer.parseInt(temp[0]);
        H = Integer.parseInt(temp[2]);

        map = new int[N][M][H];

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                temp = br.readLine().split(" ");
                for (int j = 0; j < M; j++) {
                    map[i][j][h] = Integer.parseInt(temp[j]);
                    if (map[i][j][h] == 0) {
                        zeroCnt++;
                    }
                }
            }
        }

        // 2. 익은 사과에 대하여 동시에 bfs를 돌리기위해 큐에 넣어두고 visited 처리하기
        visited = new boolean[N][M][H];
        Queue<Node> queue = new ArrayDeque<>();
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j][h] == 1) {
                        queue.offer(new Node(i, j, h, 0));
                        visited[i][j][h] = true;
                    }
                }
            }
        }

        // 3. bfs 돌려서 맞닿아있는 안익은 사과를 익은 사과로 전파하며 max depth 구하기 + 익혀진 사과 개수 구하기
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int h = node.h;
            int depth = node.depth;
            max = Math.max(depth, max);

            int nx, ny, nh, nd;
            nd = depth + 1;
            for (int[] move : moves) {
                nx = x + move[0];
                ny = y + move[1];
                nh = h + move[2];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || nh < 0 || nh >= H) {
                    continue;
                }
                if (visited[nx][ny][nh]) {
                    continue;
                }
                if (map[nx][ny][nh] == -1 || map[nx][ny][nh] == 1) {
                    continue;
                }
                visited[nx][ny][nh] = true;
                map[nx][ny][nh] = 1;
                changeCnt++; // 익혀진 사과 개수
                queue.offer(new Node(nx, ny, nh, nd));
            }
        }
        
        // 4. result + 안익은 사과와 익혀진 사과 개수 체크 하기
        System.out.println((zeroCnt == changeCnt) ? max : -1);
    }
}