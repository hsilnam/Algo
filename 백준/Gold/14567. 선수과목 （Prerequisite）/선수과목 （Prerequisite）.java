import java.io.*;
import java.util.*;

/*
입력
- N: 과목의 수 (1<=N<=1000)
- M: 선수 조건의 수 (0<=M<=500_000)
- A,B: 선수과목 조건 (A번 과목이 B번 과목의 선수 과목, A<B)
    (1<=A<B<=N)

조건
선수과목을 이수해야만 해당 과목을 이수할 수 있음
1. 한 학기에 들을 수 있는 과목 수에는 제한이 없음
2. 모든 과목은 매 학기 항상 개설

풀이
- 단방향 그래프로 만든다
- 위상정렬 이용
    - 위상정렬:
        - 순환이 없는 방향 그래프에서만 수행 가능
        - 결과 여러 개일 수 있음
        - 작업 스케줄링, 의존선 그래프 처리에 사용
    - kahn 알고리즘 이용: indegree 활용
    
출력
- 1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수할 수 있는지 출력

 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N + 1];
        int[] cnts = new int[N + 1];

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            indegree[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수 0인 과목 큐에 추가
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                cnts[i] = 1; // 진입 차수가 0인 과목은 첫 학기에 이수 가능
            }

        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                indegree[next]--;

                // 진입 차수 0인 과목 큐에 추가
                if (indegree[next] == 0) {
                    queue.offer(next);
                    cnts[next] = cnts[cur] + 1;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < N+1; i++) {
            answer.append(cnts[i]).append(" ");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}