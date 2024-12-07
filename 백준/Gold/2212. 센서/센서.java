import java.io.*;
import java.util.*;

/*
입력
- N: 센서의 개수 (1<=N<=10_000)
- K: 집중국의 개수(1<=K<=1000)
- N개의 센서의 좌표 하나의 정수로 (-1_000_000<=좌표<=1_000_000)

조건
- N개의 센서가 적어도 하나의 집중국과 통신이 가능해야함
- 고속도로 평면상의 직선으로 가정,
    센서 한 기점인 원점으로부터 정수 거리의 위치에 놓여있음
- 수신 가능 영역 기능 0이상, 좌표같을 수도 있음

풀이
- 센서들을 오름차순으로 정렬한다
- 거리 차이가 많이 나는것은 되도록이면 하나의 집중국을 가지게 하고,
    거리차이가 많이 안나는 것들은 하나의 그룹으로 묶어서 계산
    => 각 센서들 사이의 거리들을 구하고, 거리차이가 많이 나는 것을 K-1 만큼 기준으로 분리시키면
        영역의 길이가 최소가되는 그룹이 만들어진다
    - ex)
        K = 2
        - (1, 3), (6, 6, 7, 9)
        - diffs: [2, !3, 0, 1, 2]
        => 2+0+1+2

        K = 5
        - (3), (6, 7, 8, 10, 12), (14, 15), (18), (20)
        - diffs (!3, 1, 1, 2, 2, !2, 1, !3, !2)
        => 0 + 6 + 1 + 0 + 0
    => 각 센서들 사이의 거리를 구하고(N-1개),
        내림차순으로 sort하여
        (K-1)만큼 센서 사리의 거리가 큰 것부터 제외시킨다
        제외시키고 남은 센서들의 거리 구하면 최소 거리

출력
- 최대 K개의 집중국의 수신 가능 영역의 길이의 합의 최솟값

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // inputs
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int[] diffs = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diffs[i] = Math.abs(arr[i] - arr[i + 1]);
        }
        Arrays.sort(diffs);

        int sum = 0;
        for (int i = 0; i < (N - 1) - (K - 1); i++) { // 오름차순으로 정렬되어 있으니 뒤에서 부터 자름
            sum += diffs[i];
        }

        bw.write(Integer.toString(sum));

        br.close();
        bw.flush();
        bw.close();
    }
}