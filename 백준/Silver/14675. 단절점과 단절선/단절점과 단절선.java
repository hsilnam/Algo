import java.io.*;
import java.util.*;

/*
입력
- N: 트리의 정점 개수 (2<=N<=100_000)
    - 정점: 1~N
- a,b: 간선 정보, 둘이 연결되어잇음
    - 트리 보장 (1<=a,b<=N)
- q: 질의의 개수 (1<=q<=100_000)
- t,k
    - t
        - 1이면, k번 정점이 단절점인가?  (1 ≤ k ≤ n)
        - 2이면, 단절선인가? (1 ≤ k ≤ n-1)

조건
- 단절점: 해당 정점을 제거했을 때, 그래프가 2개 이상으로 나뉘는 경우
- 단절선: 해당 간선 제거했을 때, 그래프가 2개 이상으로 나뉘는 경우
- 트리: 사이클이 존재하지 않으며, 모든 정점이 연결되는 그래프

풀이
- 트리의 단절점: 그래프가 두개 이상의 컴포넌트로 분리되는 정점
       (리프노트 단절점 불가능)
- 트리의 단절선: 항상 2개로 분리

출력
- q 질의 답 출력 (yes, no)

 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());


        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (t == 1) { // 단절점
                answer.append((graph[k].size() > 1) ? "yes" : "no");
            } else if (t == 2) { // 단절선
                answer.append("yes");
            }

            answer.append("\n");
        }
        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}