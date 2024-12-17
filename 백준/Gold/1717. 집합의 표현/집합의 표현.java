import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
입력
- N: N+1집합 (0~N) (1 <= N <= 1_000_000)
- M: 연산의 개수 (1 <= M <= 100_000)
- a,b: 정수 (0 <= a, b <= n)

조건
- cmd: 0: a,b 합치기 / 1: 같은 집합인지 확인

풀이
- 유니온 파인드를 통해 합집합을 구하고 결과를 출력한다


출력
- 1로 시작하는 입력에서
    a, b가 같은 집합에 포함되어 있으면 "YES", 아니면 "NO" 출력
 */

public class Main {

    public static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        makeSet(N);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(a, b);
            } else { // 1
                answer.append((find(a) == find(b)) ? "YES" : "NO").append("\n");
            }
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void makeSet(int n) {
        parents = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
        }
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            if (pa > pb) {
                int temp = pb;
                pb = pa;
                pa = temp;
            }
            parents[pb] = pa;
        }
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}