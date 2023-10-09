/*
- 최단거리가 가장 짧은 승객 > 행 번호가 가장 작은 승객 > 열 번호가 가장 작은 승객
- 택시 = 승객: 최단거리 0
- 연료는 한 칸 이동할 때마다 1만큼 소요
- 한 승객 목적지로 이동 성공: 소모한 연료 양의 두 배가 충전
- 이동하는 도중에 바닥나면 이동에 실패, 끝 (목적지에서 0이되면 그건 실패 아님)

- 택시 - 승객 최소 / 승객 - 목적지 최소


결과: 최종적으로 남은 연료의양? (불가능하면 -1)
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static HashMap<Integer, int[][]> memberInfos;

    static class Node implements Comparable<Node> {
        int[] pos;
        int num;

        Node(int[] pos, int num) {
            this.pos = pos;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            if (this.num != o.num) {
                return Integer.compare(this.num, o.num);
            } else {
                if (this.pos[0] != o.pos[0]) {
                    return Integer.compare(this.pos[0], o.pos[0]);
                } else {
                    return Integer.compare(this.pos[1], o.pos[1]);
                }
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "pos=" + Arrays.toString(pos) +
                    ", num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int R = Integer.parseInt(temp[2]); // 자원의 양

        map = new int[N][N];
        int[] texiPos = new int[2];

        memberInfos = new HashMap();


        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            Arrays.fill(map[i], -1); // 0(기본)을 -1로 바꾸기
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(temp[j]) == 1) {
                    map[i][j] = -2; // 벽은 -2로 바꾸기
                }
            }
        }

        temp = br.readLine().split(" ");
        texiPos[0] = Integer.parseInt(temp[0]) - 1; // 0 을 기준으로 만들기
        texiPos[1] = Integer.parseInt(temp[1]) - 1; // 0 을 기준으로 만들기


        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int[][] memberInfo = new int[2][2]; // start, end
            memberInfo[0][0] = Integer.parseInt(temp[0]) - 1; // 0 을 기준으로 만들기
            memberInfo[0][1] = Integer.parseInt(temp[1]) - 1; // 0 을 기준으로 만들기

            memberInfo[1][0] = Integer.parseInt(temp[2]) - 1; // 0 을 기준으로 만들기
            memberInfo[1][1] = Integer.parseInt(temp[3]) - 1; // 0 을 기준으로 만들기
            memberInfos.put(i, memberInfo);


            // map의 start 지점에 멤버 표시하기
            map[memberInfo[0][0]][memberInfo[0][1]] = i;
        }

        int remain = R;
        while (!memberInfos.isEmpty()) {
            // 택시->승객 최단 거리 승객 구하기
            int[] tgMemberInfo = getMemberBfs(texiPos, remain);
            if (tgMemberInfo == null) { // end, 멤버 다 검사해도 불가능하면 끝
                remain = -1;
                break;
            }

            int tgMemberIdx = tgMemberInfo[0];
            int min = tgMemberInfo[1];

            // 해당 멤버 정보 저장하고 남은 멤버리스트에서 삭제하기
            int[][] tgMember = memberInfos.get(tgMemberIdx);
            memberInfos.remove(tgMemberIdx);
            map[tgMember[0][0]][tgMember[0][1]] = -1; // 지도에서도 정보 없애기

            // 승객에게 이동
            remain -= min;
            // 승객 출발지 -> 목적지로 최단 거리로 이동
            int num = bfs(tgMember[0], tgMember[1], remain);
            if (num == -1) { // end, 목적지로 이동 불가능
                remain = -1;
                break;
            }

            // 목적지 만큼 이동
            remain -= num;
            // 도착했다면 소모한 만큼 2배 충전
            remain += num * 2;
            texiPos = tgMember[1];
        }
        System.out.println(remain);
    }

    static public int[] getMemberBfs(int[] taxi, int remain) { // 멤버 번호, 사용된 자원
        boolean[][] visited = new boolean[N][N];
        visited[taxi[0]][taxi[1]] = false;
        PriorityQueue<Node> pq = new PriorityQueue<>(); // pos, move num
        pq.offer(new Node(taxi, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (map[cur.pos[0]][cur.pos[1]] >= 0) {
                return new int[]{map[cur.pos[0]][cur.pos[1]], cur.num};

            }

            int nextNum = cur.num + 1;
            for (int[] move : moves) {
                int nx = cur.pos[0] + move[0];
                int ny = cur.pos[1] + move[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (map[nx][ny] == -2) { // 벽이면 불가능
                    continue;
                }
                if (visited[nx][ny]) { // 이미 방문했으면 불가능
                    continue;
                }
                if (nextNum > remain) { // 연료보다 높아지면 불가능
                    continue;
                }
                pq.offer(new Node(new int[]{nx, ny}, nextNum));
                visited[nx][ny] = true;
            }
        }
        return null;
    }

    static public int bfs(int[] start, int[] end, int remain) {
        boolean[][] visited = new boolean[N][N];
        visited[start[0]][start[1]] = false;
        Queue<Node> queue = new ArrayDeque<>(); // pos, move num
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.pos[0] == end[0] && cur.pos[1] == end[1]) {
                return (remain >= cur.num) ? cur.num : -1; // 현재 자원의 양보다 같거나 작아야 가능, 아니면 불가능
            }

            int nextNum = cur.num + 1;
            for (int[] move : moves) {
                int nx = cur.pos[0] + move[0];
                int ny = cur.pos[1] + move[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (map[nx][ny] == -2) { // 벽이면 불가능
                    continue;
                }
                if (visited[nx][ny]) { // 이미 방문했으면 불가능
                    continue;
                }
                queue.offer(new Node(new int[]{nx, ny}, nextNum));
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}