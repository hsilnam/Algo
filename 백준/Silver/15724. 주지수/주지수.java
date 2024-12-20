import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
입력
- N,M: 영토의 크기 (1<=N<=M<=1024)
- 각 단위 구역에 살고 있는 사람 수: 1<=사람수<=100
- K: 직사각형 범위의 개수 (1<=K<=100_000)
- 직사각형 범위: x1,y1,x2,y2 (x1<=x2,y1<=y2)

조건
- 맵 1,1로 시작

풀이
- 이차원 배열 누적합
  - 더하기 쉽게 첫번째 가로, 세로에 패딩을 둔다
  - x축, y축 기준으로 순서대로 누적합 구하기
- 직사각형의 합: map[x2][y2] - map[x1 - 1][y2] - map[x2][y1 - 1] + map[x1 - 1][y1 - 1]

출력
- 직사각형 범위 내에 살고 있는 사람 수의 합
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적합 구하기
        // x축
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                map[i][j] += map[i][j - 1];
            }
        }

        // y축
        for (int j = 1; j < M + 1; j++) {
            for (int i = 1; i < N + 1; i++) {
                map[i][j] += map[i - 1][j];
            }
        }

        int K = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            answer.append(map[x2][y2] - map[x1 - 1][y2] - map[x2][y1 - 1] + map[x1 - 1][y1 - 1]).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}