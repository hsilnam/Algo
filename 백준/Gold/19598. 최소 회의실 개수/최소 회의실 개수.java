import java.io.*;
import java.util.*;

/*
- 인풋으로 times에 저장하고
    받은 회의 [시작, 끝] 시간을 시작 시간이 빠르고 짧은 것으로 정렬
- rooms의 정보를 해당 룸의 마지막 시간을 저장한다 
    - 시간을 줄이기 위해 PriorityQueue를 사용한다
        - 마지막 시간이 제일 작은 것이 현재 시작시간보다 작거나 작으면(회의 넣을 수 있으므로), 업데이트 / 크면, 룸을 새로 추가 
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] times = new int[N][2];


        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            times[i] = new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])};
        }

        Arrays.sort(times, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        PriorityQueue<Integer> rooms = new PriorityQueue(); // room end time

        for (int[] time : times) {

            int start = time[0];
            int end = time[1];

            if (!rooms.isEmpty() && rooms.peek() <= start) {
                rooms.poll();
            }
            rooms.offer(end);
        }

        bw.write(Integer.toString(rooms.size()));

        br.close();
        bw.close();
    }
}
