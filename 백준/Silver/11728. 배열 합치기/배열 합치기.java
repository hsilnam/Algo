import java.io.*;
import java.util.*;

/*
입력
- N,M: 배열A/B의 크기 (1<=N,M<=1_000_000)
- 배열 A/B의 내용 (절대값 10^9 이하)

조건
- 배열 A/B는 정렬되어있다

풀이
- 정렬되어 있으니 앞에서부터 차례대로 A,B의 원소들을 비교하면서,
    작은 숫자 순으로 새로운 배열에 집어넣는다 (크기는 N+M)

출력
- 두 배열을 합친 후 정렬한 결과 출력

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[M];
        int[] AB = new int[N + M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int aIdx = 0, bIdx = 0, abIdx = 0;
        while (aIdx < N && bIdx < M) {
            int a = A[aIdx];
            int b = B[bIdx];

            if (a <= b) {
                AB[abIdx++] = a;
                aIdx++;
            } else {
                AB[abIdx++] = b;
                bIdx++;
            }
        }


        for (int i = abIdx; i < N + M; i++) {
            if (aIdx < N) {
                AB[i] = A[aIdx++];
            } else {
                AB[i] = B[bIdx++];
            }
        }

        StringBuilder result = new StringBuilder();
        for (int ab:AB) {
            result.append(ab).append(" ");
        }

        bw.write(result.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}