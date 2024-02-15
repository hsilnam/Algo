import java.util.*;
import java.io.*;

/*
- bfs, 백팩 활용
- 스브러스는 매우 공평한 사람이기 때문에 한 아이의 사탕을 뺏으면 그 아이 친구들의 사탕도 모조리 뺏어버린다
    => 친구 집단은 하나의 묶음으로 움직인다
1. 친구 관계도를 입력받는다
2. bfs를 통해 친구 집단의 총 캔디수, 인원수를 구한다
3. 구한 집단의 총 캔디수, 인원수를 이용하여 백팩으로 가질 수 있는 최대 캔디수를 구한다
    - index: 인원수, value: 최대 캔디수
    - 현재 집단을 포함하지 않았을 때와 포함했을 때의 캔디수를 비교한다
      (arr[i] < arr[i - {현재 집단 인원수}] + {현재 집단 캔디수})
    - 해당 집단을 포함하지 않았을 때의 값과 비교해야하므로 뒤에서부터 검사한다
    - K보다 작고, 해당 집단 인원수 이상인 값을 비교한다
        - K값 이상이면 아이들이 울기 때문에 사탕을 아예 못가져간다
        - 해당 집단 인원수 이상 값을 비교해야 해당 집단을 제외한 값을 구할 수 있다 (미안이면 -1로 index out of rnage 뜸)

 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int K = Integer.parseInt(temp[2]);

        int[] candies = new int[N + 1];
        temp = br.readLine().split(" ");
        for (int i = 1; i < N + 1; i++) {
            candies[i] = Integer.parseInt(temp[i - 1]);
        }

        ArrayList<Integer>[] friends = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            friends[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            friends[a].add(b);
            friends[b].add(a);
        }

        boolean[] visited = new boolean[N + 1];
        int[] arr = new int[K];
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                int cnt = 1;
                int sum = candies[i];
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    for (int next : friends[cur]) {
                        if (visited[next]) {
                            continue;
                        }
                        cnt += 1;
                        sum += candies[next];
                        visited[next] = true;
                        queue.offer(next);
                    }
                }

                for (int j = K - 1; j >= cnt; j--) {
                    if (arr[j] < arr[j - cnt] + sum) {
                        arr[j] = arr[j - cnt] + sum;
                    }
                }
            }

        }

        int maxCandies = 0;
        for (int i = 0; i < K; i++) {
            maxCandies = Math.max(maxCandies, arr[i]);
        }

        bw.write(Integer.toString(maxCandies));

        br.close();
        bw.close();
    }

}