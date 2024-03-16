import java.util.*;
import java.io.*;

/*
- 재귀함수 사용
    - 이동방향은 순차적으로 {위,오른쪽,아래,왼쪽}를 번갈아가면서 하도록 한다 ((moveIdx+1)%4)
    - 처음 시작은 가운데에서 시작한다(N/2)
    - 좌표를 구하고 싶은 입력값 숫자를 map에 입력할 때가 오면 해당 좌표값을 따로 저장해둔다
        (단, 출력할 시 좌표값 기준이 1부터 시작이므로 +1한 값으로 출력한다(기존것은 기준이 0이라서))
    - 다음 예상 좌표의 값이,
        - 0이라면, 값을 넣을 수 있으므로 다음으로 이동한다
            - 이떄, 숫자값은 다음 숫자값으로 변경해주고(num+1), 이동방향도 꺾을 수 있도록 다음 순서로 이동해준다 ((moveIdx+1)%4)
        - 숫자라면, 값을 넣을 수 없으므로, 방향을 전의 것으로 되돌리고 값을 넣을 수 있는 구간이 나올 때까지 반복한다 ((moveIdx+3)%4)
    - 다음 예상 좌표가 map의 볌위를 넘어간다면 멈춘다
 */
public class Main {

    static int N;
    static int tg;
    static int[][] map;
    static int[] tgPos = new int[2];
    static int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        tg = Integer.parseInt(br.readLine());

        map = new int[N][N];

        makeMap(N / 2, N / 2, 0, 1);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result.append(map[i][j]).append(" ");
            }
            result.append("\n");
        }

        result.append(tgPos[0] + 1).append(" ").append(tgPos[1] + 1);
        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void makeMap(int x, int y, int moveIdx, int num) {
        if (num == tg) {
            tgPos[0] = x;
            tgPos[1] = y;
        }

        map[x][y] = num;

        int[] move = moves[moveIdx];
        int nx = x + move[0];
        int ny = y + move[1];

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            return;
        }
        if (map[nx][ny] > 0) {
            makeMap(x, y, (moveIdx + 3) % 4, num);
        } else {
            makeMap(nx, ny, (moveIdx + 1) % 4, num + 1);
        }
    }
}