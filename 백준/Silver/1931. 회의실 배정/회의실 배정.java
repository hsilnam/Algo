import java.util.*;
import java.io.*;

/*
- 최대 사용 가능한 회의의 개수:
    끝나는 시간을 기준으로 줄을 세웠을 때,
    같은 끝나는 시작 중 시작 시간이 가장 짦은 경우
1. 입력값을 PriorityQueue를 이용하여 끝나는 시간 기준으로 오름차순으로 정렬한다.
    (끝나는 시간이 동일한 경우 시작 시간 기준 오름차순 정렬)
    => 끝나는 시간을 기준으로 줄을 세웠을 때, 같은 끝나는 시작 중 시작 시간이 가장 짦은 경우을 빠르게 구하기 위해
2. 순차적으로 검사를 하면서, 겹치지 않는 경우에만 숫자를 센다
    - 겹치지 않는 경우: 전 회의의 끝시간 <= 현재 검사하는 회의의 시작시간
        - 회의 시간 개수를 센다
        - 비교하는 전 회의 시간값을 현재 회의 시간으로 업데이트한다
        - 전 회의 시간은 검사하기 쉽게 -1로 시작한다

 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // start, end
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]); // start
                }
                return Integer.compare(o1[1], o2[1]); // end
            }
        });
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            pq.offer(new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])});
        }

        int[] prev = new int[]{-1, -1};
        int cnt = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (prev[1] <= cur[0]) { // 시간이 겹치지 않은 경우
                prev[0] = cur[0];
                prev[1] = cur[1];
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));

        br.close();
        bw.close();
    }
}