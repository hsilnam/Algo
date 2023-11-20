import java.util.*;
import java.io.*;


/*
처음: 남쪽을 보며 서있음
이동: . / 벽: #
F: 앞으로 이동 / L: 방향 왼쪽, R: 방향 오른쪽 (90도, 위치는 움직이지 않음, 방향만)
*/

public class Main {

    static final int maxSize = 100;
    static final int mid = maxSize / 2;
    static int[][] moves = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; // 남 서 북 동 (L: 왼쪽으로(-1), R: 오른쪽으로(+1))

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] inputs = br.readLine().toCharArray();

        char[][] map = new char[maxSize + 1][maxSize + 1];
        for (int i = 0; i < maxSize; i++) {
            Arrays.fill(map[i], '#');
        }
        int x = mid;
        int y = mid;
        int mvIdx = 0;
        int minX = mid, maxX = mid;
        int minY = mid, maxY = mid;
        map[mid][mid] = '.'; // draw map

        for (char input : inputs) {
            if (input == 'F') {
                x += moves[mvIdx][0];
                y += moves[mvIdx][1];

                map[x][y] = '.'; // draw map
                if (mvIdx == 0) { // 남, max
                    maxX = Math.max(x, maxX);
                } else if (mvIdx == 1) { // 서, min
                    minY = Math.min(y, minY);
                } else if (mvIdx == 2) { // 북, min
                    minX = Math.min(x, minX);
                } else if (mvIdx == 3) { // 동, max
                    maxY = Math.max(y, maxY);
                }
            } else if (input == 'L') {
                mvIdx = (mvIdx + 4 - 1) % 4;
            } else if (input == 'R') {
                mvIdx = (mvIdx + 1) % 4;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                result.append(map[i][j]);
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }
}