import java.io.*;
import java.util.*;

/*
입력
- N: 회원 수 (1<=N<=50)
- 친구인 회원번호 (1~N)
- 마지막 줄 -1, -1

조건
- 가까운 정도에 따라 점수 받음
- ex) 모든 회원과 친구: 1
    다른 모든 회원이 친구 or 친구의 친구: 2
    다른 모든 회원이 친구 or 친구의 친구 or 친구의 친구의 친구: 3
- 두 회원이 친구사이 && 친구의 친구사이 = 친구사이
    A<->B<->C = A<->C
- 회장: 회원들 중 점수가 가장 작은 사람


풀이
- 플로이드 워셜 활용 (모든 정점 쌍 사이의 최단 경로)
- 각 회원의 점수를 구한 후 그 중 최소 레벨 사람들을 ㅗ른다
    - 각 회원의 점수는 가장 길게 연결된 사람의 수

출력
- 1줄: 회장 후보의 점수, 후보의 수,
  2줄: 회장 후보를 오름차순으로 모두 출력
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        final int MAX = 51;

        int[][] dist = new int[N + 1][N + 1]; // 번호 맞추기
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i != j) {
                    dist[i][j] = MAX;
                }
            }
        }

        String line;
        while (!(line = br.readLine()).isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) {
                break;
            }
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 1; k < N + 1; k++) { // 경유지
            for (int i = 1; i < N + 1; i++) { // 출발지
                for (int j = 1; j < N + 1; j++) { // 목적지
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 각 회원의 점수 구하기
        int[] levels = new int[N + 1];
        int min = MAX; // 회원 점수 중 최소값 구하기
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                levels[i] = Math.max(dist[i][j], levels[i]);
            }
            min = Math.min(levels[i], min);
        }

        // 최소 점수를 통해 회장 후보들 번호 구하기
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            if (levels[i] == min) {
                candidates.add(i);
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(min).append(" ")
                .append(candidates.size()).append("\n");
        for (int num : candidates) {
            answer.append(num).append(" ");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}