import java.io.*;
import java.util.*;

/*
입력
- 10*10 행렬 정보
    - 'B': 불 난 헛간
    - 'L': 호수
    - 'R': 돌
    - 'C': 브릿지

조건
- 동서남북 이동
- 양방향
- B에서 L로 브릿지 놓기

논리
- B: 출발지, L: 목적지, R: 지나갈 수 없음
- bfs 사용
    - 좌표값, cnt 정보 저장
    - L에 최초로 도착하는게 있다면 그게 최소값
    - R일 때 지나갈 수 없음
- 브릿지 개수 샐때, B,L포함시키지 말아야한다

출력
- 브릿지 최소 개수
 */

public class Main {

    static final int[][] moves = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = 10;
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j];
                if (map[i][j] == 'B') {
                    startX = i;
                    startY = j;
                } else if (map[i][j] == 'L') {
                    endX = i;
                    endY = j;
                }
            }
        }


        Queue<int[]> queue = new ArrayDeque<>(); // 좌표 위치, 카운트
        boolean[][] visited = new boolean[N][N];
        queue.offer(new int[]{startX, startY, 0}); // B 자기자신 제외
        visited[startX][startY] = true;
        int answer = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == endX && cur[1] == endY) {
                answer = cur[2] - 1; // L 자기자신 제외
                break;
            }
            for (int[] move : moves) {
                int nx = cur[0] + move[0];
                int ny = cur[1] + move[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == 'R') {
                    continue;
                }
                queue.offer(new int[]{nx, ny, cur[2] + 1});
                visited[nx][ny] = true;
            }
        }

        bw.write(Integer.toString(answer));

        br.close();
        bw.flush();
        bw.close();
    }
}