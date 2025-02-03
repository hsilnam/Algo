import java.io.*;
import java.util.*;

/*
입력
- N: 건물의 개수 (3<=N<=10^5)
- M: 도로의 개수 (2<=M<=5*10^5)
- a,b,c:건물의 번호(1~N), 비용(10^6)

풀이
- 절약: 전체 도로 비용의 합 - MST를 통해 구한 최소 비용
- 프림 알고리즘 이용
    - 정점의 개수보다 간선의 개수가 많기 때문에 선택
    - (N-1)*c (10^5 * 10^6)이므로 합을 구할 때 long 사용



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
        int cnt = 0;
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