import java.io.*;
import java.util.*;

/*
입력
- N, M, B: 세로,가로, 인벤토리에 있는 블럭 수 (1 ≤ M, N ≤ 500, 0 ≤ B ≤ 6.4 × 10^7)
- 땅의 높이 map (256 이하 자연수 또는 0)

조건
- 땅고르기 작업
- 집터 맨왼쪽 위 좌표 (0,0)
- 작업
    - (i,j)의 가장 위에 있는 블록 제거해서 인벤토리에 넣음 (2초)
    - 인베토리에서 블록 하나 꺼내 (i,j)의 가장 위에 이쓴 블록에 놓음 (1초)
- 집터 아래 빈공간 없음, 집터 바깥에서 블록 가져올 수 없음
- 땅의 높이는 256블록 이하 양수

풀이
- 블록 개수 int 범위 안에 있음
- 고르게 하기 위해서 빼야하는 블럭과 더해야하는 블럭의 개수를 샌다
- 0번부터 256블록을 쌓는다고 가정했을 때
    - 소지한(+얻은) 블록 개수가 사용하는 블록 개수가 적어야한다
    - 최소 시간을 가지고 높이가 가장 높은 것일 찾아야한다
    - 시간은 얻은 블록 개수*2 + 사용한 블록개수*1
    -> 우선순위 큐로 가능한 시나리오의 값을 저장해서 가장 앞단에 있는 것을 답으로 도출한다

[참고]
- StringBuilder가 ArrayList보다 문자열 조작에 더 적합해 성능이 뛰어나다
- Collections.reverse(), Collections.reverseOrder()의 차이
    - Collections.reverse(): 단순히 역순 정렬
    - Collections.reverseOrder(): 내림차순 정렬
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int B = Integer.parseInt(temp[2]);

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o2[1], o1[1]); // 높이 내림차순
                }
                return Integer.compare(o1[0], o2[0]); // 시간 오름차순
            }
        });
        for (int h = 0; h <= 256; h++) { // h높이에 맞추도록 한다
            int getCnt = 0;
            int useCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int diff = Math.abs(h - map[i][j]);
                    if (map[i][j] < h) { // 목표 h보다 작으면, 필요한 블록
                        useCnt += diff;
                    } else if (map[i][j] > h) { // 목표 h보다 크면, 얻을 수 있는 블록
                        getCnt += diff;
                    }
                }
            }
            if (B + getCnt < useCnt) { // 필요한 블록이 더많음 땅고르기 안됨
                continue;
            }
            pq.offer(new int[]{getCnt * 2 + useCnt, h}); // 시간,높이
        }

        int[] answer = pq.poll();
        bw.write(answer[0] + " " + answer[1]);

        br.close();
        bw.flush();
        bw.close();
    }

}