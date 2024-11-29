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

 */

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        int R = Integer.parseInt(temp[0]);
        int C = Integer.parseInt(temp[1]);

        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int sCnt = 0; // 양 개수
        int wCnt = 0; // 늑대 개수
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '#') {
                    continue;
                }
                if (visited[i][j]) {
                    continue;
                }

                Queue<int[]> queue = new ArrayDeque<>(); // 검사할 x,y
                queue.offer(new int[]{i, j});
                visited[i][j] = true;

                int tmpSCnt = 0; // 임시 양 개수
                int tmpWCnt = 0; // 임시 늑대 개수
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();

                    if (map[cur[0]][cur[1]] == 'k') {
                        tmpSCnt += 1;
                    } else if (map[cur[0]][cur[1]] == 'v') {
                        tmpWCnt += 1;
                    }

                    for (int[] move : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
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

                if (tmpSCnt > 0 && tmpWCnt > 0) {
                    if (tmpSCnt > tmpWCnt) { //양이 이김
                        tmpWCnt = 0;
                    } else {
                        tmpSCnt = 0;
                    }
                }

                sCnt += tmpSCnt;
                wCnt += tmpWCnt;
            }
        }

        bw.write(sCnt + " " + wCnt);

        br.close();
        bw.close();
    }
}