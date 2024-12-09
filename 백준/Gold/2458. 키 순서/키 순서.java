import java.io.*;
import java.util.*;

/*
입력
- N: 학생들의 수 (1<=N<=500)
- M: 한쌍의 키를 비교한 횟수 (0<=M<=N(N-1)/2)
- 두학생의 키를 결과들 a,b
    - 양수, 학생 번호
    - a<b<N

조건
- 단방향
- 정확히 자신의 키를 알 수 있는 건 나의 키를 전부다 아는 경우

풀이
- 플로이드 워셜 활용 (모든 정점 쌍 사이의 최단 경로)
- 모두가 자신을 아는 학생이 몇인지 출력
    - (자신보다 작은 학생 + 자신보다 큰 학생의 수)가 N-1(자기자신 제외)이어야 한다

출력
- 자신의 키가 몇번째인지 알수 있는 학생 수
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        final int MAX = 501;

        int[][] dist = new int[N + 1][N + 1]; // 번호맞추기

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i != j) {
                    dist[i][j] = MAX;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
        }

        for (int k = 1; k < N + 1; k++) { // 경유지
            for (int i = 1; i < N + 1; i++) { // 출발지
                for (int j = 1; j < N + 1; j++) { // 목적지
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        // 자기를 알고, 자기가 아는 사람들 카운트 구하기
        int[] cnts = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j || dist[i][j] == MAX) {
                    continue;
                }
                cnts[i] += 1;
                cnts[j] += 1;
            }
        }


        int answer = 0;
        for (int i = 1; i < N + 1; i++) {
            if (cnts[i] == N - 1) {
                answer += 1;
            }
        }

        bw.write(Integer.toString(answer));

        br.close();
        bw.flush();
        bw.close();
    }
}