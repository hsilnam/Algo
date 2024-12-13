import java.io.*;
import java.util.*;

/*
입력
- N: 시간 (0<=N<=23)
- K: 포함되는 숫자 (0<=K<=9)

조건
- 00:00:00 ~ N:59:59의 모든 시각 중, K가 하나라도 포함되는 모든 시각 세기
- 초단위로만 시각 구분

풀이
- 시,분,초 올려서 직접 찾기

출력
-  00:00:00 ~ N:59:59의 모든 시각 중, K가 하나라도 포함되는 시각들의 수
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int h = 0;
        int m = 0;
        int s = 0;

        int cnt = 0;
        while (h <= N && m <= 59 && s <= 59) {
            if (h % 10 == K || h / 10 % 10 == K ||
                    m % 10 == K || m / 10 % 10 == K ||
                    s % 10 == K || s / 10 % 10 == K) {
                cnt++;
            }
            s++;
            if (s == 60) {
                m++;
                s = 0;
            }
            if (m == 60) {
                h++;
                m = 0;
            }
        }

        bw.write(String.valueOf(cnt));

        br.close();
        bw.flush();
        bw.close();
    }
}