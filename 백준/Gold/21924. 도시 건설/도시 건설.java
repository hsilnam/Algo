import java.io.*;
import java.util.*;

/*
입력
- N: 건물의 개수 (3<=N<=10^5)
- M: 도로의 개수 (2<=M<=5*10^5)
- a,b,c:건물의 번호(1~N), 비용(10^6)

풀이
- 절약: 전체 도로 비용의 합 - MST를 통해 구한 최소 비용
- (N-1)*c (10^5 * 10^6)이므로 합을 구할 때 long 사용
- 2가지 알고리즘 구현: 프림보다 크루스칼이 조금 더 빠름
    - 프림 알고리즘 이용
        - 중간 정도의 밀집도라 덜 빠른 듯하다
          (극단적인 밀집그래프가 아님)
        - 정점을 하나씩 넓혀가는 거라 우선순위 큐의 연산이 많아짐
    - 크루스칼 알고리즘
        - 정렬 후 유니온 파인드(o(1)), 간선 빠르게 처리

출력
- 예산을 얼마나 절약할 수 있는지
- 모든 건물이 연결되어 있지 않으면 -1



[참고]
MST
- 가중치 그래프 (모든 정점 포함)
    - N개의 정점: 간선 개수 반드시 N-1
- 최소한의 가중치의 합으로 연결
- 사이클 없어야함
- 여러 개의 최소 신장 트리 존재 가능

크루스칼 알고리즘
- 간선 중심
- 가중치가 작은 간선 부터 선택 (정렬할 때 우선순위 큐 이용)
- 유니온-파인드 이용
- O(ElogE)
- 간선 개수가 적을 때 유리

프림 알고리즘
- 프림 알고리즘
- 임의의 정점에서 시작, 인접한 최소 가중치 간선 선택
- 우선순위 큐 이용
- O(ElogV)
- 정점 개수가 적을 때 유리
- 실전에서는 더 많이 사용
 */
public class Main {
    public static void main(String[] args) throws Exception { // prim
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] graph = new ArrayList[N + 1]; // 1~
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        long total = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});

            total += c;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // b, c
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]); // c
            }
        });

        long answer = -1;
        long sum = 0;
        int cnt = 0; // 정점
        boolean[] visited = new boolean[N + 1];
        pq.offer(new int[]{1, 0});
        while (!pq.isEmpty()) {
            if(cnt == N){
                answer = total - sum;
                break;
            }
            int[] cur = pq.poll();
            int b = cur[0];
            int c = cur[1];
            if (visited[b]) {
                continue;
            }
            cnt++;
            visited[b] = true;
            sum += c;
            for (int[] next : graph[b]) {
                int nb = next[0];
                if (visited[nb]) {
                    continue;
                }
                pq.offer(next);
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.flush();
        bw.close();
    }
}



/* // 크루스칼 풀이: 1020ms
public class Solution3 {
    public static int[] parents;
    public static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        long total = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // a, b, c
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]); // c
            }
        });

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new int[]{a, b, c});

            total += c;
        }

        make();

        long answer = -1;
        long sumc = 0;
        int cnt = 0; // 간선
        while (!pq.isEmpty()) {
            if (cnt == N - 1) {
                answer = total - sumc;
                break;
            }
            int[] node = pq.poll();
            int a = node[0];
            int b = node[1];
            long c = node[2];

            if (find(a) != find(b)) {
                union(a, b);
                sumc += c;
                cnt++;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void make() {
        parents = new int[N + 1]; // 1~
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
    }

    public static void union(int a, int b) {
        int ap = find(a);
        int bp = find(b);
        if (ap > bp) {
            int temp = ap;
            ap = bp;
            bp = temp;
        }

        if (ap != bp) {
            parents[bp] = ap;
        }
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}
 */
