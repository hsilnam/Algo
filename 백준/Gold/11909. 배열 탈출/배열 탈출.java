import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int x;
        int y;
        int price;

        public Node(int x, int y, int price) {
            this.x = x;
            this.y = y;
            this.price = price;
        }
    }

    static int[][] moves = {{1, 0}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int[][] mins = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(mins[i], Integer.MAX_VALUE);
        }
        mins[0][0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.price, o2.price);
            }
        });
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                break;
            }
            for (int[] move : moves) {
                int nx = cur.x + move[0];
                int ny = cur.y + move[1];

                if (nx >= N || ny >= N) {
                    continue;
                }

                int price = (map[cur.x][cur.y] > map[nx][ny]) ? 0 : map[nx][ny] - map[cur.x][cur.y] + 1;

                if (mins[nx][ny] > mins[cur.x][cur.y] + price) {
                    mins[nx][ny] = mins[cur.x][cur.y] + price;
                    pq.offer(new Node(nx, ny, mins[nx][ny]));
                }
            }
        }

        bw.write(Integer.toString(mins[N - 1][N - 1]));

        br.close();
        bw.close();
    }
}