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
- sol1) 시,분,초 올려서 직접 찾기
- sol2) 각 최대 숫자 범위에서 K가 들어가는 개수 미리 찾고 계산하기

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


        int cntMk, cntSK;
        cntMk = cntSK = countK(60, K);
        int cnt = 0;
        for (int i = 0; i <= N; i++) {
            if (containK(i, K)) {
                cnt += 60 * 60; // 모든 분,초 포함
            } else {
                // (K가 포함된 분 -> 모든 초 포함 + K) + (K가 포함되지 않은 분 -> K가 포함된 초 포함)
                cnt += cntMk * 60 + (60 - cntMk) * cntSK;
            }

            // sol1)
        }
        /*
        int h = 0;
        int m = 0;
        int s = 0;
        int cnt = 0;
        while (h <= N && m <= 59 && s <= 59) {
            if (containK(h,K) ||
                    containK(m,K) ||
                    containK(s,K)) {
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
        */
        bw.write(String.valueOf(cnt));

        br.close();
        bw.flush();
        bw.close();
    }

    public static int countK(int maxRange, int K) {
        int cnt = 0;
        for (int i = 0; i < maxRange; i++) {
            if (containK(i, K)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static boolean containK(int num, int K) {
        return (num % 10 == K || num / 10 % 10 == K);
    }
}