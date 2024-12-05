import java.io.*;
import java.util.*;

/*
입력
- N: 줄의 개수(1<=N<=500_000, 홀수)
- 정수들 (절댓값 4_000 이하)

조건
- 산술평균: (N개의 수들의 합)/N
- 중앙값: 오름차순 나열 후 중앙값
- 최빈값: N개의 수들 중 가장 많이 나타나는 값
- 범위: 최댓값과 최솟값의 차이

풀이
- 중앙값, 범위를 구하기 위해 오른차순으로 정렬한다
- 산술평균은 double로 계산한다
- 최빈값은 노드(정수값, 개수) 1차원 배열을 통해 구한다
    1. 처음에는 -4000 ~ 4000 값을 담기 위해 8001(0포함) 개의 배열을 만들고
      중간값을 0으로 설정하여 적절한 자리에서 개수를 추가하도록한다
      ex) -4000 -> idx:0, 0 -> idx: 4000
        => idx = 정수값+4000
    2. 개수 내림차수, 정수값 오른차순으로 정렬한다
    3. 가장 앞에 위치한 정수값을 가져온다
        단, 뒤의 값의 개수도 같다면 뒤에있는 정수값으로 가져온다

출력
- 순차적으로 출력
    산술평균(소수점 이하 첫째자리 반올림),
    중앙값,
    최빈값(여러개 있을 땐 두번째로 작은 값으로),
    범위
 */

public class Main {


    public static class Node implements Comparable<Node> {
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cnt == o.cnt) {
                return Integer.compare(this.num, o.num); // 정수값 오름차순
            }
            return Integer.compare(o.cnt, this.cnt); // 개수 내림차순
        }

        @Override
        public String toString() {
            return num + " " + cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        // 산술평균 구하기
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }
        double mean = (double) sum / (double) N;
        mean = Math.round(mean);
        int answer1 = (int) mean;

        // 중앙값 구하기
        int answer2 = nums[(N - 1) / 2];

        // 최빈값 구하기
        Node[] numCnts = new Node[8002]; // 총 8001개
        for (int i = 0; i < 8002; i++) {
            numCnts[i] = new Node(i - 4000, 0);
        }

        for (int num : nums) {
            numCnts[num + 4000].cnt += 1;
        }
        Arrays.sort(numCnts);

        int answer3 = (numCnts[0].cnt != numCnts[1].cnt) ? numCnts[0].num : numCnts[1].num;

        // 범위 구하기
        int answer4 = nums[N - 1] - nums[0];

        StringBuilder answer = new StringBuilder();
        answer.append(answer1).append("\n");
        answer.append(answer2).append("\n");
        answer.append(answer3).append("\n");
        answer.append(answer4).append("\n");

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

}