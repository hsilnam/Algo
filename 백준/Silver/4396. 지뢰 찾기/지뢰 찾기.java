import java.io.*;
import java.util.*;

/*
입력
- N: 양의 정수 (0<N<=10)
- gameBoard: 지뢰의 위치
    - .: 지뢰 X
    - *: 지뢰 O
- actionBoard:
    - x: 이미 열린 칸
    - .: 열리지 않은 칸

조건
- N*N 사이즈
- m개의 지뢰
- 열렸는데 지뢰 없는부분: 인접한 8개 칸 검사해서 지뢰 개수 표시하기

풀이
- gameBoard, actionBoard 2차원 배열로 저장
- boomCntBoard 2차원 배열을 만듦
    - gameBoard에 대한 정보를 입력받을 때,
        해당 지점이 폭탄이면,
        boomCntBoard에 해당 지점 걱 인접한 8개 칸의 값을 +1
- 열림 여부에 대한 input을 받으면서 순차적으로 검사한다
    각 gameboard의 칸을 순서대로 검사하고 프린트
    - 해당 지점이 이미 열린칸이라면,
        - resultBoard의 해당 지점에 boomCntBoard의 값을 프린트
     - 이때, 폭탄이 하나라도 터지면 모든 폭탄을 '*'로 프린트
    - 그 외: '.' 프린트

출력
- 각각의 위치가 정확하게 채워진 칸을 표현
    - 지뢰가 없으면서 열린 칸 1~7
    - 지뢰가 있는 칸을 열었다면 지뢰가 있는 모든 칸이 *로 표시
    - 다른 모든 지점은 .
 */

public class Main {

    final static int[][] boomAroundMoves = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        char[][] gameBoard = new char[N][N];
        int[][] boomCntBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                gameBoard[i][j] = input.charAt(j);
                if (gameBoard[i][j] == '*') {
                    for (int[] move : boomAroundMoves) {
                        int nx = i + move[0];
                        int ny = j + move[1];
                        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                            boomCntBoard[nx][ny]++;
                        }
                    }
                }
            }
        }

        boolean isBoom = false;
        char[][] actionBoard = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                actionBoard[i][j] = input.charAt(j);
                if (!isBoom &&
                        actionBoard[i][j] == 'x' &&
                        gameBoard[i][j] == '*') { // 폭탄이 하나라도 터지면
                    isBoom = true;
                }
            }
        }


        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (actionBoard[i][j] == 'x' && gameBoard[i][j] == '.') { // 열렸는데 빈칸
                    result.append(boomCntBoard[i][j]);
                    continue;
                }
                result.append((isBoom) ? gameBoard[i][j] : ".");
            }
            result.append("\n");
        }

        bw.write(result.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}