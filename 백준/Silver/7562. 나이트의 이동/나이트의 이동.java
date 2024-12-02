import java.io.*;
import java.util.*;

/*
입력
- T: 테스트케이스 개수
- I: 체스판 길이 (4<=I<=300)
    - 체스판 크기는 I*I
- 나이트 좌표 출발지, 목적지
    - 좌표: 0~I-1

조건
- 나이트의 다음 이동:
    - (x-1, y-2)
    - (x-2, y-1)
    - (x-1, y+2)
    - (x-2, y+1)
    - (x+1, y+2)
    - (x+2, y+1)
    - (x+1, y-2)
    - (x+2, y-1)

논리
- bfs 사용
- 움직인 횟수 오름차순을 기준으로 잡는 우선순위 큐 이용
- 목표 좌표일때의 움직인 횟수 출력

출력
- 갹 테슽케이스마다 나이트 최소 몇번 만에 이동할 수 있는지 출력

 */

public class Main {

    static final int[][] moves = {
            {-1, -2}, {-2, -1}, {-1, 2}, {-2, 1},
            {1, 2}, {2, 1}, {1, -2}, {2, -1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int I = Integer.parseInt(br.readLine());
            String[] temp = br.readLine().split(" ");
            int startX = Integer.parseInt(temp[0]);
            int startY = Integer.parseInt(temp[1]);
            temp = br.readLine().split(" ");
            int endX = Integer.parseInt(temp[0]);
            int endY = Integer.parseInt(temp[1]);


            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[2], o2[2]);
                }
            }); // x, y, cnt
            boolean[][] visisted = new boolean[I][I];
            pq.offer(new int[]{startX, startY, 0});
            visisted[startX][startY] = true;

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if (cur[0] == endX && cur[1] == endY) {
                    answer.append(cur[2]).append("\n"); // cnt
                    break;
                }
                for (int[] move : moves) {
                    int nx = cur[0] + move[0];
                    int ny = cur[1] + move[1];

                    if (nx < 0 || nx >= I || ny < 0 || ny >= I) {
                        continue;
                    }
                    if (visisted[nx][ny]) {
                        continue;
                    }
                    pq.offer(new int[]{nx, ny, cur[2] + 1});
                    visisted[nx][ny] = true;
                }
            }
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}