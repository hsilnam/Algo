import java.util.*;
import java.io.*;

/*
 - 폭탄과 봄버맨의 움직임: 번갈아가면서 동작
    - 봄버맨 (i%2 == 0): 폭탄 설치
    - 폭탄 (i%2 == 1): 3초전에 설치된 폭탄이 폭발하여 상하좌우가 파괴 (나중에 설치된 폭탄도 파괴)
 - 전체 탐색을 돌린다
    - 문제의 조건
        - (i%2 == 1)인 시점에 모든 비어있는 맵에 폭탄을 설치한다
        - 폭탄의 연쇄반응은 없다
        - 1 <= R,C,N <= 200
    => 전체 탐색을 돌린다
        (200*200*200 < 1초)
        - 폭탄의 위치와 시간 정보를 저장하는 배열을 따로 만든다.
          배열에는 폭탄이 터질 시간을 저장한다 (+3)
          (queue로 만들면 나중에 이전 폭탄으로 사라지게된 폭탄을 지울 수 없다)
 */
public class Main {
    public static int R, C;
    public static char[][] map;
    public static int[][] booms;
    public static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        int N = Integer.parseInt(temp[2]);

        map = new char[R][C];
        booms = new int[R][C];
        // 0초,1초
        for (int i = 0; i < R; i++) {
            char[] tempC = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (tempC[j] == 'O') {
                    booms[i][j] = 3; // 설치된 시점부터 시작해서 2초 후에 터진다
                }
                map[i][j] = tempC[j];
            }
        }

        // 2초부터 시작
        for (int sec = 2; sec <= N; sec++) {
            if (sec % 2 == 0) {
                installBoom(sec);
            } else if (sec % 2 == 1) {
                doBoom(sec);
            }
        }

        bw.write(makeInfoMap());

        br.close();
        bw.close();
    }

    public static void installBoom(int sec) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    map[i][j] = 'O';
                    booms[i][j] = sec + 3;
                }
            }
        }
    }

    public static void doBoom(int sec) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (booms[i][j] == sec) {
                    map[i][j] = '.';
                    for (int[] move : moves) {
                        int nx = i + move[0];
                        int ny = j + move[1];
                        if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                            continue;
                        }
                        // 다음번에 터질 폭탄인 경우 폭탄을 제거한다
                        // 현재 터질 폭탄에는 영향을 주지 않기위하여
                        if (map[nx][ny] == 'O' && booms[nx][ny] != sec) {
                            map[nx][ny] = '.';
                        }
                    }
                    booms[i][j] = 0;
                }
            }
        }
    }

    public static String makeInfoMap() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                result.append(map[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }
}