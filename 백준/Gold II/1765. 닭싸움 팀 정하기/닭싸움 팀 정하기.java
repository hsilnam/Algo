import java.util.*;
import java.io.*;

/*
- 분리집합(union,find), bfs 이용
- 사람 번호는 1을 기준으로
1. 친구, 적에 대한 관계를 입력받는다 (Fs,Es)
2. '내 친구의 친구는 내 친구이다'를 만족하는 것을 묶기 위해 각 노드에 대하여 bfs를 돌려,
    union(나, 내 친구)을 수행한다.
3. '내 원수의 원수도 내 친구이다'를 만족하는 것을 묶기 위해 리프 노드에 대하여 bfs를 돌려,
    union(나, 내 원수의 원수)를 수행한다.
    - 리프 노드에서부터 시작해야 '원수의 원수'를 오류없이 편하게 구할 수 있다
4. HashSet을 이용하여, 그룹의 종류를 추린 후 그룹의 개수를 출력한다.
    - union이 전부 끝난 후에도, 업데이트되지 않은 것이 있을 수 있기 때문에,
      find로 그룹의 종류를 판별한다
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

        // f of f
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
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

        /*
        for (int i = 1; i < N + 1; i++) {
            for (int next : Fs[i]) {
                union(i, next);
            }
        }
         */

        // e of e
        visited = new boolean[N + 1];
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
