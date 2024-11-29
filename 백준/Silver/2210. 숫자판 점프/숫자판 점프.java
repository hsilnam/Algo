import java.io.*;
import java.util.*;

/*

제한: 2초

입력
- 5*5크기의 숫자판 정보

조건
- 임의의 위치에서 시작
- 인접한 네방향 5번 이동
- 재방문 가능

로직
- dfs와 hashset을 사용해서 완전탐색하기
    - 순차적으로 위치를 검사해서 6자리 만들때까지 순회하고 hashset으로 중복 제거
    - 5*5*4*4*4*4*4*4 = 102_400
    => 제한시간 2초라서 완전탐색 돌려도 괜찮다


출력
- 만들수있는 6자리 수

 */

public class Main {

    public static final int N = 5; // 배열크기
    public static final int K = 6; // 자리수
    public static int[][] map = new int[N][N];
    public static HashSet<Integer> set = new HashSet<>();
    public static int[] nums = new int[K];
    public static int[][] moves = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j, 0);
            }
        }

        bw.write(Integer.toString(set.size()));

        br.close();
        bw.close();
    }

    public static void dfs(int x, int y, int idx) {
        if (K == idx) {
            StringBuilder numStr = new StringBuilder();
            for (int i = 0; i < K; i++) {
                numStr.append(nums[i]);
            }
            set.add(Integer.parseInt(numStr.toString()));
            return;
        }

        nums[idx] = map[x][y];

        for (int[] move : moves) {
            int nx = x + move[0];
            int ny = y + move[1];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            dfs(nx, ny, idx + 1);
        }
    }
}