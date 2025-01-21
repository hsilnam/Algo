import java.io.*;
import java.util.*;

/*
입력
- N: 지시 횟수 (1 <= N <= 10^6)
- I a b: a, b 같은 로봇 1 <= a, b <= 106, a != b, a, b는 정수)
- Q c: 지금까지의 robot c의 부품개수 질문
    - 적어도 하나 들어옴

풀이
- union find 사용해서 그룹화 하기
    - 무슨 로봇이 들어올지 모르니 최대 10^6의 크기로 초기화하기
    - 이 과정에서 size계산도 같이 업데이트 해준다

출력
- 부품 개수 출력 (Q)



[실패]
- 1% (시간초과)
    - union find 끝난 후 size계산 따로 해주기
    -> 매 쿼리마다 모든 부품 순회해야함

 */

public class Main {

    public static int[] parents;
    public static int[] sizes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int MAX_PARTS = 1_000_000;
        makeSet(MAX_PARTS);

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("I")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            } else if (cmd.equals("Q")) {
                int c = Integer.parseInt(st.nextToken());
                answer.append(getSize(c)).append("\n");
            }
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void makeSet(int n) {
        parents = new int[n + 1];
        sizes = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            if (sizes[pa] < sizes[pb]) {
                int temp = pa;
                pa = pb;
                pb = temp;
            }
            parents[pb] = pa;
            sizes[pa] += sizes[pb];
        }
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]); // 경로 압축
    }

    public static int getSize(int a) {
        return sizes[find(a)];
    }
}