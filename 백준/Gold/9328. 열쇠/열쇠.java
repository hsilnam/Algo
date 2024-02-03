import java.util.*;
import java.io.*;

/*

 */
public class Main {
    public static int N, M;
    public static int docTotalCnt, docCnt;
    public static char[][] map;
    public static int keys;
    public static Queue<int[]> waitQueue;

    public static int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            // get inputs
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);

            docTotalCnt = 0;
            docCnt = 0;
            keys = 0;
            map = new char[N][M];
            ArrayList<int[]> checkEdges = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                char[] tempC = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (tempC[j] == '$') {
                        docTotalCnt++;
                    }
                    if (tempC[j] != '*'
                            && (i == 0 || i == N - 1 || j == 0 || j == M - 1)) {
                        checkEdges.add(new int[]{i, j});
                    }
                    map[i][j] = tempC[j];
                }
            }


            for (char c : br.readLine().toCharArray()) {
                if(c == '0') {
                    break;
                }
                keys = addAlpha(keys, c);
            }


            // 일단 1차적으로 한 번 쭉 돌기
            waitQueue = new ArrayDeque<>();
            for (int[] checkEdge : checkEdges) {
                if (docTotalCnt == docCnt) {
                    break;
                }
                bfs(checkEdge[0], checkEdge[1]);
            }

            // 막혔던 부분 한번 더 돌려보기 (한바뀌 다 돌았는데 크기가 그대로면 멈추기)
            int size = waitQueue.size();
            int cnt = 0;
            int solvedCnt = 0;
            while (!waitQueue.isEmpty()) {
                if (docTotalCnt == docCnt) {
                    break;
                }
                if (cnt == size) {
                    if (solvedCnt == 0) {
                        break;
                    }
                    size = waitQueue.size();
                    cnt = 0;
                    solvedCnt = 0;
                }
                cnt++;

                int[] cur = waitQueue.poll();
                if (!containAlpha(keys, Character.toLowerCase(map[cur[0]][cur[1]]))) {
                    waitQueue.offer(cur);
                    continue;
                }

                solvedCnt++;
                bfs(cur[0], cur[1]);
            }

            result.append(docCnt).append("\n");
        }
        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void bfs(int x, int y) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];

            if (isUpperCase(map[cx][cy])
                    && !containAlpha(keys, Character.toLowerCase(map[cx][cy]))) {
                waitQueue.add(new int[]{cx, cy});
                continue;
            }

            if (map[cx][cy] == '$') {
                docCnt++;
            } else if (isLowerCase(map[cx][cy])) {
                keys = addAlpha(keys, map[cx][cy]);
            }

            map[cx][cy] = '*'; // 해결된 것은 더이상 중복검사하지않게 막음

            for (int[] move : moves) {
                int nx = move[0] + cx;
                int ny = move[1] + cy;
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == '*') {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    public static int addAlpha(int alphas, char c) {
        if (isUpperCase(c)) {
            c -= 'A';
        } else if (isLowerCase(c)) {
            c -= 'a';
        }
        return alphas | (1 << c);
    }

    public static boolean containAlpha(int alphas, char c) {
        if (isUpperCase(c)) {
            c -= 'A';
        } else if (isLowerCase(c)) {
            c -= 'a';
        }
        return ((alphas & (1 << c)) != 0);
    }

    public static boolean isUpperCase(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    public static boolean isLowerCase(char c) {
        return (c >= 'a' && c <= 'z');
    }
}