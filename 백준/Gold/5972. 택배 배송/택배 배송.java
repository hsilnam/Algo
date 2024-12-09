import java.io.*;
import java.util.*;

/*
입력
- N, M: 헛간, 양방향 길 (1<=N,M<=50_000)
- Ai,Bi,Ci: 길정보
    - A,B: 이어진 헛간
    - Ci: 길 소 개수 (0<=Ci<=1_000)

조건
- 하나 이상의 길로 연결 가능
- 출발 1-> N
- 소만나면 여물 줘야함

풀이
- Dijstra 활용

출력
- 농부 현서가 가져가야 될 최소 여물

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        ArrayList<int[]>[] graph = new ArrayList[N + 1]; // 1맞춰주기
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }


        int[] mins = new int[N + 1];
        Arrays.fill(mins, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        pq.offer(new int[]{1, 0});
        mins[1] = 0;


        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (mins[cur[0]] < cur[1]) {
                continue;
            }

            for (int[] next : graph[cur[0]]) {
                if (mins[next[0]] == Integer.MAX_VALUE || mins[next[0]] > mins[cur[0]] + next[1]) {
                    mins[next[0]] = mins[cur[0]] + next[1];
                    pq.offer(new int[]{next[0], mins[next[0]]});
                }
            }
        }

        bw.write(Integer.toString(mins[N]));

        br.close();
        bw.flush();
        bw.close();
    }
}