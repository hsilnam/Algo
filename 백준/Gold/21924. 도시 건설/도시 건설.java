import java.io.*;
import java.util.*;

/*
- MST (크루스칼)
 */
public class Main {
    static int[] parents; // i번째 멤버의 부모 정보들

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        parents = new int[N + 1];
        ArrayList<int[]> nodes = new ArrayList<>();

        long total = 0;
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]);
            int to = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);
            total += weight;
            nodes.add(new int[]{from, to, weight});
        }

        Collections.sort(nodes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        makeSet(N);

        long sum = 0;
        int cnt = 0;
        boolean isOk = false;
        for (int[] node : nodes) {
            if (union(node[0], node[1])) {
                sum += node[2];
                cnt++;

                if (cnt == N - 1) {
                    isOk = true;
                    break;
                }
            }
        }

        bw.write(Long.toString((isOk) ? (total - sum) : -1));

        br.close();
        bw.close();
    }

    public static void makeSet(int n) {
        for (int i = 1; i <= n; i++) {
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