import java.io.*;
import java.util.*;

/*
입력
- N: 강의의 개수 (1<=N<=100_000)
- 강의 번호 (1~N, 무작위, 한번씩반)
  강의 시작 시간, 강의 종료 시간 (0<=t<=1_000_000_000, 시작 < 종료)

조건
- 한강의실에 동시에 강의 진행 불가능
- A 종료시간, B 시작시간 겹치는건 상관 없음

풀이
- 강의 우선순위 큐를 만들어서 강의를 순서대로 넣는다
  (시작 시간이 빠른 시간, 끝시간이 빠르게 끝나는 시간으로 정렬하여)
- 우선 방 우선순위 큐를 만들어서 현재 진행중인 강의의 끝시간을 저장한다
    (오름차순)
- 강의 우선순위 큐에서 하나씩 꺼내서 비교한다
    - 검사한 강의를 방은 방 우선순위큐에 추가
    - 진행중인 강의의 끝시간 < 검사하려는 강의의 시작시간: 겹침
        - 룸개수를 추가
    - 진행중인 강의의 끝시간 >= 검사하려는 강의의 시작시간: 겹치지 않음
        - 진행중이던 강의가 끝났으니, 해당 강의는 삭제



- ex)
               15----21
                   20-----25
    3----8
  2-----------14
       6----------------------27
        7---13
          12 -----18
       6-----------20
- 만약 현재 진행중인 강의가 끝나기 전까지 겹치는 수업이 있다면 강의실 개수를 늘린다

출력
- 필요한 최소 강의실의 개수

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> lectures = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]); // 시작시간
            }
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken(); // 강의 번호
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.offer(new int[]{start, end});
        }

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        int[] curLec = lectures.poll(); // 검사할 강의
        rooms.offer(curLec[1]); //end // 진행중인 강의

        int roomCnt = 1;
        while (!lectures.isEmpty()) {
            curLec = lectures.poll();

            if (rooms.peek() > curLec[0]) {
                roomCnt++;
            } else {
                rooms.poll(); // 삭제
            }
            rooms.offer(curLec[1]);
        }

        bw.write(Integer.toString(roomCnt));

        br.close();
        bw.flush();
        bw.close();
    }
}