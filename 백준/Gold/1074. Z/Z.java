import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
- 재귀
    - 제일 작은 패턴: 사각형을 4등분으로 쪼갰을 시 Z모양 순서 대로 검사한다
    - 종료조건: 더이상 사각형을 나눌 수 없게 되었을 시 (n이 0이 경우),
            해당 자리에 몇번째로 방문했는지 카운트한다.
    - 추가 종료조간:
        1) 타켓 위치에 방문 정보를 얻게되었을 시,
            뒤의 방문 정보는 더이상 필요하지 않으므로
            전체 탐색을 종료시킨다
        2) 검사하려는 사각형에 타켓 좌표가 포함되어있지 않으면,
            해당 사각형은 검사하지 않고 사각형 전체에 대하여 방문을 한꺼번에 카운트하고 넘어간다
    - 파라미터
        - n: 현재 사각형의 2의 배수 크기
            (사각형의 길이는 2^n)
        - startX, startY, 현재 사각형의 시작 좌표
 */
public class Main {

    public static int N, r, c, cnt;
    public static int answer;
    public static boolean isEnd;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        r = Integer.parseInt(temp[1]);
        c = Integer.parseInt(temp[2]);

        explore(N, 0, 0);

        bw.write(Integer.toString(answer));

        br.close();
        bw.close();
    }

    public static void explore(int n, int startX, int startY) {
        if (isEnd) {
            return;
        }

        int len = (int) Math.pow(2, n);
        if (!(startX <= r && r < (startX + len) &&
                startY <= c && c < (startY + len))) {
            cnt += (int) Math.pow(len, 2);
            return;
        }

        if (n == 0) { // 2^0
            if (startX == r && startY == c) {
                answer = cnt;
                isEnd = true;
            }
            cnt++;
            return;
        }

        int nextN = n - 1; // 2^(n-1)
        int nextLen = (int) Math.pow(2, nextN);

        explore(nextN, startX, startY);
        explore(nextN, startX, startY + nextLen);
        explore(nextN, startX + nextLen, startY);
        explore(nextN, startX + nextLen, startY + nextLen);
    }
}