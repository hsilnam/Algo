import java.io.*;
import java.util.*;

/*
입력
- N,M:  (1<=N<=10_000, 1<=M<=100_000)
- A,B: A가 B를 신뢰한다
- 컴퓨터 번호 1~N

조건
- A,B이면 A도 해킹 가능: A<-B 이런식으로 전파
- bfs로 모든 정점에서 최대 길이 구하고, 제일 길이가 큰 것들을 출력

풀이
- 해킹 가능: A<-B 이런식으로 전파 (단방향)
- bfs를 통해 전파하는 컴퓨터 개수 센다
- !시간초과 해결을 위해:
    - Queue LinkedList롤 구현
      삽입/삭제가 빈번하고 큐의 크기가 큰 지금의 상황에서
      ArrayDeque는 내부 크기 부족하면 O(N) 복사작업 일어나 느림
      (LinkedList는 삽입/삭제 O(1))
    - 자주사용하는 변수들을 static으로 선언
      (static 변수: 클래스 로딩 시 한번만 생성, 프로젝트 종료될 때까지 메모리 유지. 변수 생성,해제 반복하지 않아도됨)


출력
- 한번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호 오름차순

[오답노트]
- 1%(메모리초과)
    - 플로이드 워샬로 품
        플로이드 워샬: O(N) 10^6은 너무 큼
    -> 문제를 잘못 이해함!! 최대 정점 거리 인걸 뽑는 줄 알았음
        전파하는 수니 bfs로 전파되는 노드의 개수를 세면 된다 (visited 제외)

[참고]
모든 정점까지의 거리
- N이 작을 때: 플로이드 워샬을 사용
    - O(N^3)
    - N <=500 !!
- N이 크고 가중치가 없는 경우: BFS로 해결
    - O(N*(V+E))
- N이 크고 가중치가 있는 경우: 다익스트라를 여러 번 실행
    - O((V+E)*logV)
 */

public class Main {
    static int N,M,a,b,totalMax;
    static ArrayList<Integer>[] graph;
	static boolean[] visited;
    static int[] cnts;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1]; // 번호 맞추기
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph[a].add(b); // 전파방향 b->a
        }


        cnts = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            // bfs
            Queue<Integer> queue = new LinkedList<>(); // 번호
            queue.offer(i);
            visited = new boolean[N + 1];
            visited[i] = true;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                        cnts[next]++;
                    }
                }
            }
        }

        totalMax = 0;
        for (int cnt : cnts) {
            totalMax = Math.max(totalMax, cnt);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            if (cnts[i] == totalMax) {
                answer.append(i).append(" ");
            }
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
