import java.util.*;
import java.io.*;

/*
 */
public class Main {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] Fs = new ArrayList[N + 1];
        ArrayList<Integer>[] Es = new ArrayList[N + 1];
        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            Fs[i] = new ArrayList<>();
            Es[i] = new ArrayList<>();
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int p = Integer.parseInt(temp[1]);
            int q = Integer.parseInt(temp[2]);

            if ("F".equals(temp[0])) {
                Fs[p].add(q);
                Fs[q].add(p);
            } else {
                Es[p].add(q);
                Es[q].add(p);
            }
        }

        // e of e
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (isLeaf(Es[i]) && !visited[i]) {
                visited[i] = true;
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();

                    for (int next : Es[cur]) {
                        if (visited[next]) {
                            continue;
                        }
                        visited[next] = true;
                        queue.offer(next);

                        for (int next2 : Es[next]) {
                            if (visited[next2]) {
                                continue;
                            }
                            union(cur, next2);
                        }
                    }
                }
            }
        }


        // f of f
        /*
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (isLeaf(Fs[i])) {
                visited[i] = true;
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    for (int next : Fs[cur]) {
                        if (visited[next]) {
                            continue;
                        }

                        union(cur, next);

                        queue.offer(next);
                        visited[next] = true;
                    }
                }

            }
        }
         */

        for (int i = 1; i < N + 1; i++) {
            for (int next : Fs[i]) {
                union(i, next);
            }
        }


        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i < N + 1; i++) {
            set.add(find(i));
        }

        bw.write(Integer.toString(set.size()));

        br.close();
        bw.close();
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            parents[pb] = pa;
        }
    }

    public static int find(int num) {
        if (parents[num] == num) {
            return num;
        }
        return parents[num] = find(parents[num]);
    }

    public static boolean isLeaf(ArrayList<Integer> node) {
        return (node.size() == 1);
    }
}