import java.io.*;
import java.util.*;

/*
입력
- N: 도시의 수 (2 <= N <= 10)
- W: 비용 행렬
	- W[i][j]: 도시 i에서 j로 가기위한 비용 (0 <= W[i][j] <= 1,000,000)
  	  - 0이면 갈 수 없음

조건
- 어느 한 도시에서 출발
- N개의 도시를 모두거쳐 다시 원래 도시로 돌아옴
- 단, 한 번 갔던 도시로는 못감
- 단방향 행렬 (가는 것과 오는 것 비용 같지 않음)
- W[i][i] = 항상 0

풀이
- 0부터 시작해서모든 도시 탐색
    - 순열을 이용해서 방문 시뮬레이션 돌리기 (포함시켰다가 안시켰다가)
        - 이미 방문한 도시나 이동 불가능한 경우는 제외
    - 뽑은 순열에서 최소비용 구하기

출력
- 최소 비용

 */

public class Main {

    static int N;
    static int[][] W;
    static int min = Integer.MAX_VALUE;
    static int[] nums;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        nums = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        perm(0);

        bw.write(String.valueOf(min));

        br.close();
        bw.flush();
        bw.close();
    }
    static void perm(int num) {
        if (num == N) {
            checkMin(nums);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            if (num != 0 && W[nums[num - 1]][i] == 0) {
                continue;
            }
            nums[num] = i;
            visited[i] = true;
            perm(num + 1);
            visited[i] = false;
        }
    }


    static void checkMin(int[] nums) {
        int totalW = 0;
        int weight;
        for (int i = 0; i < N; i++) {
            weight = W[nums[i % N]][nums[(i + 1) % N]]; // 현재 도시에서 다음 도시로 이동
            if (weight == 0) {
                return;
            }
            totalW += weight;
        }
        min = Math.min(totalW, min);
    }

}