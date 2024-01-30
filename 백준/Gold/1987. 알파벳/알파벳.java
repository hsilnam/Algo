import java.util.*;
import java.io.*;

/*
- dfs로 탐색
- 비트마스킹으로 알파벳 존재 여부 체크
    - 알파벳은 다음과 같이 매핑: (A(0)~Z(25))
    - 비트마스킹(2^26) int(2^32)로 커버 가능
    - 알파벳 존재 여부를 통해 체크하므로 자연스럽게 지나왔던 길도 방문처리되어 제외됨

---
[실패들]
- 14% (메모리초과)
    - bfs로 검사하고 ArrayDeque 사용
 */
public class Main {
    static int[][] moves = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static int R, C;
    static char[][] map;
    static int max = 1;

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

        dfs(0, 0, addAlpha(0, map[0][0]), 1);

        bw.write(Integer.toString(max));

        br.close();
        bw.close();
    }

    public static void dfs(int x, int y, int visitedAlphas, int cnt) {
        for (int[] move : moves) {
            int nx = move[0] + x;
            int ny = move[1] + y;
            int nCnt = cnt + 1;
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }
            if (containsAlpha(visitedAlphas, map[nx][ny])) {
                continue;
            }
            max = Math.max(nCnt, max);
            dfs(nx, ny, addAlpha(visitedAlphas, map[nx][ny]), nCnt);
        }
    }

    public static int getAlphaNum(char alpha) { // A~Z: 0~25
        return (alpha - 'A');
    }

    public static int addAlpha(int visitedAlphas, char alpha) {
        return (visitedAlphas | (1 << getAlphaNum(alpha)));
    }

    public static boolean containsAlpha(int visitedAlphas, char alpha) {
        return ((visitedAlphas & (1 << getAlphaNum(alpha))) != 0);
    }
}