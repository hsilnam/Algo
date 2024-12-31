import java.io.*;
import java.util.*;

/*
입력
- V: 정점의 개수(1 ≤ V ≤ 10_000)
- E: 간선의 개수(1 ≤ E ≤ 100_000)
- A,B,C: 간선 정보
    - 정점(A,B)사이의 가중치(C)
    - C: 음수일 수도 있음, 절댓값이 1_000_000을 넘지 않음
    - 정점: 1~V
    - -2_147_483_648 <= 최소 스패닝 트리의 가중치 <= 2_147_483_648

조건
- 최소스패닝 트리: 그래프의 모든 정점을 연결하는 부분 그래프 중, 가중치 합이 최소인 틑리

풀이
- MST: prim 이용


출력
- 최소 스패닝 트리의 가중치 출력
 */

public class Main {

    public static int[] parents; // i번째 멤버의 부모 정보들

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1]; // padding 0
        ArrayList<int[]> nodes = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            nodes.add(new int[]{from, to, weight});
        }

        Collections.sort(nodes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        makeSet(V);

        long sum = 0;
        for (int[] node : nodes) {
            if (union(node[0], node[1])) {
                sum += node[2];
            }
        }

        bw.write(Long.toString(sum));


        br.close();
        bw.flush();
        bw.close();
    }

    public static void makeSet(int v) {
        for (int i = 1; i <= v; i++) {
            parents[i] = i;
        }
    }

    public static int findSet(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = findSet(parents[a]);
    }

    public static boolean union(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a == b) {
            return false;
        } else {
            parents[b] = a;
        }
        return true;
    }
}