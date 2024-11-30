import java.io.*;
import java.util.*;

/*
제한: 2초

입력
- T: tc (1<=T<=20)
- N: 지원자의 수 (1<=N<=100_000)
- 각 지원자의 서류심사 성적, 면접 성적 순위
- 동석차 없음

조건
- 서류심사 성적, 명접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선별

로직
- 1등 먼저 카운트
- 서류 심사 결과로 오름차순 후,
    앞에 있는 지원자들(서류심사 성적이 이미 자신보다 높음)보다 면접시험 결과가 높을 때만 카운트

출력
- 각 테케 별, 선별 가능한 신입사원 최대 인원수

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {

            int N = Integer.parseInt(br.readLine());
            int[][] scores = new int[N][2]; //서류심사 성적, 명접시험 성적

            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split(" ");
                scores[i][0] = Integer.parseInt(temp[0]);
                scores[i][1] = Integer.parseInt(temp[1]);
            }

            Arrays.sort(scores, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0], o2[0]);
                }
            });

            int cnt = 1;
            int top = scores[0][1];
            for (int i = 1; i < N; i++) {
                if (top > scores[i][1]) {
                    cnt++;
                    top = scores[i][1];
                }
            }
            answer.append(cnt).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.close();
    }
}