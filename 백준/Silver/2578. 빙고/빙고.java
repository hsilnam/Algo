import java.io.*;
import java.util.*;

/*
입력
- 5*5 빙고판 정보
- 5*5 사회자가 부르는 수
- 각 수는 1~25, 한번씩 사용

조건
- 대각선, 가로, 세로 다 체워지면 선 긋기
- 선이 세개 이상 그어지는 순간 빙고

풀이
- Hashmap으로 해당 숫자 위치 저장 (빠르게 접근할 수 있게)
- 5*5크기의 체크맵을 만들어서 검사 (0:아직 안불림, 1: 불림)
- 선 검사
    - 대각선: map[i][i], map[i][4-i]의 값이 모두 같을 때
    - 가로/세로 기준 한줄의 값이 모두 같을 때
    -> 체크맵의 값이 전부 1일 때 선
    - 각 경우에 대하여 선이 이미 만들어졌는지 boolean 정보를 저장하여 다시 검사하지 않도록 한다

출력
- 사회자가 몇번째 수를 부른 후 빙고를 외치는지 출력

 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = 5;

        int[][] checkMap = new int[N][N];
        HashMap<Integer, int[]> numPosMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                numPosMap.put(num, new int[]{i, j});
            }
        }

        boolean lDiagonal = false;
        boolean rDiagonal = false;
        boolean[] rowLine = new boolean[N];
        boolean[] colLine = new boolean[N];

        int lineCnt = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                int[] numPos = numPosMap.get(num);
                checkMap[numPos[0]][numPos[1]] = 1;

                // 대각선 체크
                if (!lDiagonal) {
                    int sum = 0;
                    for (int k = 0; k < N; k++) {
                        sum += checkMap[k][k];
                    }
                    if (sum == N) {
                        lDiagonal = true;
                        lineCnt++;
                    }

                }
                if (!rDiagonal) {
                    int sum = 0;
                    for (int k = 0; k < N; k++) {
                        sum += checkMap[k][N - 1 - k];
                    }
                    if (sum == N) {
                        rDiagonal = true;
                        lineCnt++;
                    }
                }

                // 가로줄 체크
                for (int r = 0; r < N; r++) {
                    if (rowLine[r]) {
                        continue;
                    }
                    int sum = 0;
                    for (int c = 0; c < N; c++) {
                        sum += checkMap[r][c];
                    }
                    if (sum == N) {
                        rowLine[r] = true;
                        lineCnt++;
                    }
                }

                // 세로줄 체크
                for (int c = 0; c < N; c++) {
                    if (colLine[c]) {
                        continue;
                    }
                    int sum = 0;
                    for (int r = 0; r < N; r++) {
                        sum += checkMap[r][c];
                    }
                    if (sum == N) {
                        colLine[c] = true;
                        lineCnt++;
                    }
                }

                if (lineCnt >= 3) { // 빙고
                    bw.write(String.valueOf(i * N + (j + 1)));
                    bw.flush();
                    return;
                }


            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}