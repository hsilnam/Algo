import java.util.*;
import java.io.*;

/*
- 플로이드 워셜 활용
1. 플로이드 워셜을 적용하기 쉽게 그래프를 만든다
    - 초기값
        - 자기자신이면 값 0으로 설정
        - 자기자신이 아니면 값 최대값으로 설정
    - 주어진 입력값은 A,B의 관계는 1단계 이므로 1로 설정 (A <-> B)
2. 플로이드 워셜을 통해 각 지점까지의 최단 거리를 구한다
3. 케빈 베이컨 수가 가장 작은 사람을 출력한다 (같은 경우는 번호가 가장 작은 사람 출력)
    - 1번부터 N번까지 순차적으로 플로이드 워셜로 구한 거리들을 통해 케빈 베이컨 수(전체 합)를 구한다
    - 케빈 베이션 수를 통해 최소값을 가진 작은 사람 번호를 저장한다
    - 1번부터 순차적으로 검사하므로 최소값으로 대체된 값이 번호가 가장작은 사람이다
4. 결과값을 출력한다
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int MAX = 100_000;

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    graph[i][j] = MAX;
                }
            }
        }
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]) - 1; // 시작점 0으로 맞추기
            int to = Integer.parseInt(temp[1]) - 1; // 시작점 0으로 맞추기
            graph[from][to] = graph[to][from] = 1;
        }


        for (int k = 0; k < N; k++) { // mid
            for (int i = 0; i < N; i++) { // from
                for (int j = 0; j < N; j++) { // to
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int minIdx = 0;
        int min = MAX;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += graph[i][j];
            }
            if (min > sum) {
                min = sum;
                minIdx = i;
            }
        }
        bw.write(Integer.toString(minIdx + 1));// 기준점 1로 맞춰주기

        br.close();
        bw.close();
    }
}