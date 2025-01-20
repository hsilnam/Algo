import java.io.*;
import java.util.*;

/*
입력
- A: 사람수 (A <= 2_000)
- T: 구하고자하는 번째 (T <= 10_000)
- 구하고자 하는 구호가 “뻔”이면 0, “데기”면 1

조건
- n-1회차 문장일 때는 ‘뻔 – 데기 – 뻔 – 데기 – 뻔(x n번) – 데기(x n번)
- 원을 돌아 다시 일구 차례가 와도 게임은 계속 진행
- 일구가 0번째이고, 반 시계 방향으로 번데기 게임을 진행
- 1회차부터 시작

풀이
- 완전탐색
- 이번 회차의 뻔데기 정보를 저장한 arr을 만든 후 그 arr를 검사하며,
    구하고자 하는 구호를 카운트한다
- 전 라운드의 길이의 누적합을 구한다
- 구하고자하는 조건에 들어맞으면,
    전 라운드의 누적합과 현재 라운드 내에서의 번째를 구한 후 사람수로 나눈 나머지를 구해 몇번째인지 구한다

출력
- 구하고자 하는 사람이 원탁에서 몇 번째에 있는지 정수로 출력

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int A = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());
        int type = Integer.parseInt(br.readLine());


        int n = 1;
        int tCnt = 0;
        int prevRoundSum = 0;
        while (true) {
            int[] arr = new int[4 + 2 * (n + 1)];
            arr[0] = arr[2] = 0;
            arr[1] = arr[3] = 1;
            for (int i = 0; i < n + 1; i++) {
                arr[i + 4] = 0;
                arr[i + 4 + n + 1] = 1;
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == type) {
                    tCnt++;
                    if (tCnt == T) {
                        bw.write(String.valueOf((prevRoundSum + i) % A));

                        br.close();
                        bw.close();
                        return;
                    }
                }
            }
            prevRoundSum += arr.length;
            n++;
        }
    }
}