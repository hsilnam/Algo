import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static HashSet<Integer>[] likes;

    static int[][] moves = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    static class SeatInfo implements Comparable<SeatInfo> {
        int r, c;
        int likeNum;
        int emptyNum;

        public SeatInfo(int r, int c, int likeNum, int emptyNum) {
            this.r = r;
            this.c = c;
            this.likeNum = likeNum;
            this.emptyNum = emptyNum;
        }

        @Override
        public int compareTo(SeatInfo o) {
            if (this.likeNum != o.likeNum) {
                return (-1) * Integer.compare(this.likeNum, o.likeNum);
            }
            if (this.emptyNum != o.emptyNum) {
                return (-1) * Integer.compare(this.emptyNum, o.emptyNum);
            }
            if (this.r != o.r) {
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.c, o.c);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], -1);
        }

        Queue<Integer> sQueue = new ArrayDeque<>();
        likes = new HashSet[N * N]; // index가 학생 번호
        for (int i = 0; i < N * N; i++) { // 숫자는 0 ~ (N-1)로 통일한다
            String[] temp = br.readLine().split(" ");
            int tg = Integer.parseInt(temp[0]) - 1;
            sQueue.offer(tg);
            likes[tg] = new HashSet<>();
            likes[tg].add(Integer.parseInt(temp[1]) - 1);
            likes[tg].add(Integer.parseInt(temp[2]) - 1);
            likes[tg].add(Integer.parseInt(temp[3]) - 1);
            likes[tg].add(Integer.parseInt(temp[4]) - 1);
        }

        while (!sQueue.isEmpty()) {
            int sNum = sQueue.poll();
            int[] sSeat = getStudentSeat(sNum);
            map[sSeat[0]][sSeat[1]] = sNum;
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int likeNum = getSeatInfo(i, j).likeNum;
                if (likeNum == 0) {
                    continue;
                }
                total += Math.pow(10, likeNum - 1);
            }
        }

        bw.write(Integer.toString(total));

        br.close();
        bw.close();
    }

    static public int[] getStudentSeat(int sNum) {
        PriorityQueue<SeatInfo> pq = new PriorityQueue<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] != -1) { // 이미 정해진 자리면 넘어가기
                    continue;
                }
                pq.offer(getSeatInfo(x, y, sNum));
            }
        }
        SeatInfo seatInfo = pq.poll();
        return new int[]{seatInfo.r, seatInfo.c};
    }

    static public SeatInfo getSeatInfo(int x, int y) {
        return getSeatInfo(x, y, map[x][y]);
    }

    static public SeatInfo getSeatInfo(int x, int y, int sNum) {
        int likeNum = 0;
        int emptyNum = 0;
        for (int[] move : moves) {
            int nx = x + move[0];
            int ny = y + move[1];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if (map[nx][ny] == -1) { // 빈공간
                emptyNum++;
                continue;
            }
            if (likes[sNum].contains(map[nx][ny])) { // 좋아하는 학생 있으면
                likeNum++;
            }
        }
        return new SeatInfo(x, y, likeNum, emptyNum);
    }
}