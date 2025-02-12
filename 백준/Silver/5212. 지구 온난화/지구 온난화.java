import java.io.*;
import java.util.*;


public class Main {
    public static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception { // prim
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        boolean[][] flood = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    int cnt = 0;
                    for (int[] move : moves) {
                        int nx = i + move[0];
                        int ny = j + move[1];
                        if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == '.') {
                            cnt++;
                        }
                    }
                    if (cnt >= 3) {
                        flood[i][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (flood[i][j]) {
                    map[i][j] = '.';
                }
            }
        }


        int startX = R;
        int endX = 0;
        int startY = C;
        int endY = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    startX = Math.min(startX, i);
                    endX = Math.max(endX, i);
                    startY = Math.min(startY, j);
                    endY = Math.max(endY, j);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                answer.append(map[i][j]);
            }
            answer.append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}