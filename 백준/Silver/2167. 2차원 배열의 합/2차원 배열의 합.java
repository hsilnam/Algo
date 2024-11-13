import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
2차원 배열의 누적합을 통해 범위의 합 구하기
- 누적합을 편하게 계산하기 위해 첫번째 행,열을 0으로 패딩을 준다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int[][] map = new int[N + 1][M + 1]; // 첫번째 행과 열에는 0으로 패딩
        for (int i = 1; i <= N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(temp[j - 1]);
            }
        }

        // 이차원 누적합 배열 만들기
        // x축
        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                map[i][j] += map[i][j - 1];
            }
        }

        // y축
        for (int j = 0; j < M + 1; j++) {
            for (int i = 1; i < N + 1; i++) {
                map[i][j] += map[i - 1][j];
            }
        }

        // 배열의 합 구하기
        int K = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int k = 0; k < K; k++) {
            temp = br.readLine().split(" ");
            int i = Integer.parseInt(temp[0]);
            int j = Integer.parseInt(temp[1]);
            int x = Integer.parseInt(temp[2]);
            int y = Integer.parseInt(temp[3]);


            int sum = map[x][y] - map[x][j - 1] - map[i - 1][y] + map[i - 1][j - 1];
            result.append(sum).append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void printMap(int[][] map) { // 디버깅용
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                temp.append(map[i][j]).append(" ");
            }
            temp.append("\n");
        }
        temp.append("\n");

        System.out.println(temp.toString());
    }
}