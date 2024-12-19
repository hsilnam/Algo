import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
입력
- N: 돌개수 (1<=N<=1000)

조건
- 두 명이서 게임
- 번갈아가면서 돌 가져감
    - 돌은 1개 or 3개 가져갈 수 있음
- 마지막 돌을 가져간 사람이 게임 이김
- 상근이가 먼저 시작

풀이
- ex)
    1: s
    s
    1

    2: c
    s c
    1 1

    3: s
    s
    111

    4: c
    s   c
    111 1

    5: s
    s   c s
    111 1 1

    6: c
    s   c
    111 111
    ...
- N이 홀수이면 SK, 짝수이면 CY
    - 나머지가 0,2이면 그대로
    - 나머지가 1이면 반대로

출력
- 상근이가 이기면 SK, 창영이가 이기면 CY
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        bw.write((N % 2 == 1) ? "SK" : "CY");

        br.close();
        bw.flush();
        bw.close();
    }
}