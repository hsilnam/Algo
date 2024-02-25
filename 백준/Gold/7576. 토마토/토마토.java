import java.util.*;
import java.io.*;

/*
- bfs 사용: (x,y, 날짜 수) 정보를 담고있음
    동시에 퍼져나갈 수 있도록함
1. 입력값을 받으면서 토마토들의 정보를 저장하고,
    안 익은사과(0)의 개수를 세고,
    bfs로 검사를 할 익은 사과(1)의 정보를 queue에 넣는다.
    - 안 익은사과 개수: 검사를 마치고 난 후의 안익은 사과 개수를 용이하게 세기 위하여
    - 익은 사과 정보 업데이트
        - queue에 검사 시작 지점으로 넣어준다.
        - 검사하는 익은 사과 영향이 동시에 퍼저나갈 수 있도록 (x,y,0)으로 초기화해서 넣어준다
        - 해당 좌표에 대하여 방문 처리를 해준다
2. bfs로 검사하는 익은 사과 주의의 안익은 사과들을 익혀가며 일수와 안 익은 사과 개수의 정보를 업데이트한다.
    - 토마토가 없는 곳(-1)은 넘어간다
    - 토마토에 접근할 수 있다면,
        - 해당 좌표의 토마토의 상태를 익은 상태(1)로 업데이트한다
        - 방문 처리를 해준다
        - 안익은 사과 개수를 하나 줄여준다
        - 날짜 개수를 하나 올려준다
    - 마지막 날짜 개수 저장한다.
3. 결과값을 출력한다
    - 안익은 사과 개수가 있다면, -1을 출력한다
    - 없다면, 마지막 날짜 개수를 출력한다.
 */
public class Main {
    public static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int M = Integer.parseInt(temp[0]);
        int N = Integer.parseInt(temp[1]);

        int[][] map = new int[N][M];
        Queue<int[]> queue = new ArrayDeque<>(); // x, y, dayCnt
        boolean[][] visited = new boolean[N][M];
        int zeroCnt = 0;
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
                if (map[i][j] == 1) {
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                } else if (map[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }

        int dayCnt = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (queue.isEmpty()) {
                dayCnt = Math.max(dayCnt, cur[2]);
            }

            int nDayCnt = cur[2] + 1;
            for (int[] move : moves) {
                int nx = cur[0] + move[0];
                int ny = cur[1] + move[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == -1) {
                    continue;
                }

                queue.offer(new int[]{nx, ny, nDayCnt});
                map[nx][ny] = 1;
                visited[nx][ny] = true;
                zeroCnt--;
            }
        }

        int result = (zeroCnt == 0) ? dayCnt : -1;
        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }
}