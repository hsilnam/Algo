import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
입력
- N: 풍선 수 (1<=N<=1_000)
- 풍선 안 종이에 적혀잇는 수 (0제외 정수)

조건
1. 1번 풍선 터뜨림
2. 종이에 적혀있는 값만큼 이동하여 다음 풍선 터뜨림
    - 양수: 오른쪽
    - 음수: 왼쪽
    - 이미 터진 풍선 제외
- 써클로 이루어져있음  1<->N

풀이
- 풍선의 번호와 쪽지를 차례대로 덱에 넣어둔다
- 목표 풍선을 덱에서 아예 꺼내다
- 양수이면 앞에서 x-1만큼 꺼내서 뒤로 보내고,
    음수이면 뒤에서 x-1만큼 꺼내서 앞으로 보낸다
- 전부 나올 때까지 반복한다

출력
- 터진 풍선의 번호 차례로 나열

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int note = Integer.parseInt(st.nextToken());
            deque.offer(new int[]{i, note});
        }

        int[] cur = deque.pollFirst();
        int[] nums = new int[N];
        int idx = 0;
        while (!deque.isEmpty()) {
            nums[idx++] = cur[0];
            for (int i = 0; i < Math.abs(cur[1]) - 1; i++) {
                if (cur[1] < 0) {
                    deque.offerFirst(deque.pollLast());
                } else {
                    deque.offerLast(deque.pollFirst());
                }
            }
            cur = (cur[1] < 0) ? deque.pollLast() : deque.pollFirst();
        }
        nums[idx++] = cur[0];


        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            answer.append(nums[i]).append(" ");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}