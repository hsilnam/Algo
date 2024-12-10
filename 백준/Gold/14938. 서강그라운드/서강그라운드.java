import java.io.*;
import java.util.*;

/*
입력
- N: 지역의 개수 (1<=N<=100)
- M: 수색범위 (1<=M<=15)
- R: 길의 개수 (1<=R<=100)
- T: N개의 숫자, 각 구역에 있는 아이템 수(1<=t<=30)
- a,b,l: 지역번호(1<=a,b<=n), 길의길이(1<=l<=15)

조건
- 양방향
- 수색범위 이내의 모든 지역의 아이템 습득 가능


풀이
- 플로이드 워샬 활용


출력
- 얻을 수 있는 최대 아이템 개수

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int R = Integer.parseInt(temp[2]);

        int[] items = new int[N + 1]; //시작점 맞춰주기
        temp = br.readLine().split(" ");
        for (int i = 1; i < N + 1; i++) {
            items[i] = Integer.parseInt(temp[i - 1]);
        }

        int[][] graph = new int[N + 1][N + 1]; // 시작점 맞춰주기
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i != j) {
                    graph[i][j] = 1501;
                }
            }

        }

        for (int i = 0; i < R; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            int l = Integer.parseInt(temp[2]);
            graph[a][b] = l;
            graph[b][a] = l;
        }


        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }


        int answer = 0;
        for (int i = 1; i < N + 1; i++) {
            int sum = 0;
            for (int j = 1; j < N + 1; j++) {
                if (graph[i][j] <= M) {
                    sum += items[j];
                }
            }
            answer = Math.max(sum, answer);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.flush();
        bw.close();
    }
}