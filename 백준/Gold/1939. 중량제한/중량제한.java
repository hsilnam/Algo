import java.io.*;
import java.util.*;

/*
입력
- N: 섬 개수 (2<=N<=10_000)
- M: 다리 정보 개수 (1<=M<=100_000)
- A,B,C: 다리 정보 (1<=A,B<=N, 1<=c<=1_000_000_000)
    - A <-> B (양방향)
    - C: 중량제한
- a,b: 공장이 위치해있는 섬의 번호

조건
- 모든 다리는 양방향
- 서로 같은 두섬 사이에 여러 개의 다리가 있을 수 있다
- 중량제한 초과하는 양의 물품은 다리를 건널 수 없다
- 공장이 있는 두섬을 연결하는 경로는 항상 존재

풀이
- 같은 두섬 사이에는 다리 길이 최댓값을 대표 무게로 취급
- 이분 탐색 이용
    - 가능한 weight를 이분 탐색을 통해 탐색한다
    - 가능 여부는 bfs로 해당 weight일 때 정상적으로 도착할 수 있는지로 판단한다

출력
- 한번의 이동에서 옮길 수 있는 물품 중량의 최댓값
 */

public class Main {
    public static ArrayList<int[]>[] graph;
    public static int start, end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        graph = new ArrayList[N + 1]; // 도시 번호 맞춰주기, num, weight
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }


        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int A = Integer.parseInt(temp[0]);
            int B = Integer.parseInt(temp[1]);
            int C = Integer.parseInt(temp[2]);
            min = Math.min(min, C);
            max = Math.max(max, C);
            graph[A].add(new int[]{B, C});
            graph[B].add(new int[]{A, C});
        }

        temp = br.readLine().split(" ");
        start = Integer.parseInt(temp[0]);
        end = Integer.parseInt(temp[1]);

        int left = min;
        int right = max;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(Integer.toString(right));

        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isPossible(int weight) {
        Queue<Integer> queue = new ArrayDeque();
        queue.offer(start);
        boolean[] visited = new boolean[graph.length];
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == end) {
                return true;
            }
            for (int[] next : graph[cur]) {
                if (visited[next[0]]) {
                    continue;
                }
                if (next[1] < weight) {
                    continue;
                }
                queue.offer(next[0]);
                visited[next[0]] = true;
            }
        }
        return false;
    }

}