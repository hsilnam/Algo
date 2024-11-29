import java.io.*;
import java.util.*;

/*

제한: 1초

입력
- R,C: 배열 크기 3 ≤ R, C ≤ 250)
- 배열 정보
    - .: 빈공간
    - #: 울타리
    - v: 늑대
    - k: 양

조건
- 울타리로 막히지 않은 영역에는 양,늑대 없음
- 대각선 이동 모두 불가능
- 울타리 내
    - 양들의 숫자 > 늑대의 숫자: 늑대가 잡아 먹힘
    - 그 외: 양들이 잡아먹힘

로직
- dfs와 hashset을 사용해서 완전탐색하기
    - 순차적으로 위치를 검사해서 6자리 만들때까지 순회하고 hashset으로 중복 제거
    - 5*5*4*4*4*4*4*4 = 102_400
    => 제한시간 2초라서 완전탐색 돌려도 괜찮다
- BFS 이용 해서 영역 분별하기
    - 같은 영역 내에
        - 늑대와 양이 같이 있다면 세력다툼 해서 이긴쪽이 살아남음
        - 같이 있지 않다면, 그대로 살아있음

출력
- 살아남는 양과 늑대의 수


[BFS vs DFS]
bfs: 18332kb, 180ms
dfs: 18872kb, 160ms

이 문제에서는 DFS가 더 빠르다
- BFS: 큐 동작(삽입, 제거)이 재귀보단 오버헤드가 더 발생
    최단 경로 탐색이 보장되어 최단 경로 구할 때 좋음
    깊이가 얕고 노드가 많을 수록 유리
- DFS: 탐색 깊이가 제한적이므로 재귀 깊이가 제한,
    재귀 호출 (Stack) 메모리 적게 사용
    깊이가 깊고 노드가 적을 수록 유리
O(V+E)
 */

public class Solution {
    public static final int[][] MOVES = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static char[][] map;
    public static int R, C;
    public static boolean[][] visited;
    public static int tmpSCnt; // 임시 양 개수
    public static int tmpWCnt; // 임시 늑대 개수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int sCnt = 0; // 양 개수
        int wCnt = 0; // 늑대 개수
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '#') {
                    continue;
                }
                if (visited[i][j]) {
                    continue;
                }
                tmpSCnt = 0; // 임시 양 개수
                tmpWCnt = 0; // 임시 늑대 개수

                DFS(i, j);
//                BFS(i, j);


                if (tmpSCnt > tmpWCnt) {
                    sCnt += tmpSCnt;
                } else {
                    wCnt += tmpWCnt;
                }
            }
        }

        bw.write(sCnt + " " + wCnt);

        br.close();
        bw.close();
    }

    public static void DFS(int x, int y) {
        visited[x][y] = true;

        if (map[x][y] == 'k') {
            tmpSCnt += 1;
        } else if (map[x][y] == 'v') {
            tmpWCnt += 1;
        }

        for (int[] move : MOVES) {
            int nx = x + move[0];
            int ny = y + move[1];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }
            if (visited[nx][ny]) {
                continue;
            }
            if (map[nx][ny] == '#') {
                continue;
            }
            DFS(nx, ny);
        }

    }

    public static void BFS(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>(); // 검사할 x,y
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (map[cur[0]][cur[1]] == 'k') {
                tmpSCnt += 1;
            } else if (map[cur[0]][cur[1]] == 'v') {
                tmpWCnt += 1;
            }

            for (int[] move : MOVES) {
                int nx = cur[0] + move[0];
                int ny = cur[1] + move[1];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == '#') {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
}
