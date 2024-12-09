import java.io.*;
import java.util.*;

/*
입력
- N: 맵의 길이 (1<=N<=50)
- 맵 정보: 0: 검 방, 1: 흰 방

조건
- n*n
- 검은 방: 들어갈 수 없음
- 서로 붙어있는 두 개의 흰방: 지나다닐 수 있음
- (0,0),(n-1,n-1): 항상 흰방, 시작과 끝지점


풀이
- Dijstra 활용
    - weight 흰방은 0, 검은방은 1

출력
- 흰 방으로 바꾸어야 할 최소의 검은 방

 */

public class Main {

    static int[][] moves = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static class Node implements Comparable<Node> {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        int[][] dist = new int[N][N]; //  해당 위치의 weight 값
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.x == N-1 && cur.y == N-1) { // 도착했을 시
                break;
            }
            if (dist[cur.x][cur.y] < cur.cnt) { // 더 걸리는 경우로 온 경우
                continue;
            }

            for (int[] move : moves) {
                int nx = cur.x + move[0];
                int ny = cur.y + move[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                int nCnt = cur.cnt + ((map[nx][ny] == 1) ? 0 : 1); // 다음방 들럈을 때, 예상 cnt
                if (dist[nx][ny] > nCnt) { // 들려서 이득인 경우만 업데이트
                    dist[nx][ny] = nCnt;
                    pq.offer(new Node(nx, ny, nCnt));
                }
            }
        }

        bw.write(Integer.toString(dist[N-1][N-1]));

        br.close();
        bw.flush();
        bw.close();
    }
}