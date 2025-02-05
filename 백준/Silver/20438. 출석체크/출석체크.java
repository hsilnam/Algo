import java.io.*;
import java.util.*;

/*
입력
- N: 학생의 수
- K: 졸고 있는 학생의 수
- Q: 지환이가 출석 코드를 보낼 학생의 수
- M: 주어질 구간의 수
    - 1 <= K, Q <= N <= 5_000, 1 <= M <= 50_000
- 졸고 있는 학생의 입장 번호
- 출석 코드를 받을 학생의 입장 번호
- 구간의 범위: S, E (3 <= S < E <=N+2)

조건
- 접속 순서대로 3~N+2 입장번호 받음
- 한 학생에게 출석 코드 보냄 -> 본인 입장 번호의 배수인 학생들에게 출석 코드 보냄
    = 해당 강의 출석
- 졸고 있는 학생들 출석코드 제출X, 보내지 않음
- 무작위 한 명에게 출석 코드를 보내는 행위를 Q번 반복,
    특정 구간의 입장 번호를 받은 학생 중 출석 되지 않은 학생들의 수

풀이
- int 배열에 학생들의 출석여부를 저장한다
    - 0: 출석, 1: 출석하지 않음, -1: 조는 학생 표시
    - 출석 안한 걸로 초기화
- 조는 학생은 -1으로 만든다
- 출석 코드를 보내고 받는 학생들을 1로 만든다
- 3번쨰 학생 부터 누적합을 구한다. 이때 -1은 0으로 변경해서 구한다
- 구간합을 end-start로 계산해서 구한다

출력
- 각 구간에 대해 출석이 되지 않은 학생들의 수
 */
public class Main {
    public static void main(String[] args) throws Exception { // prim
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int K = Integer.parseInt(st.nextToken()); // 조는 학생 수
        int Q = Integer.parseInt(st.nextToken()); // 출석 코드 받은 학생 수
        int M = Integer.parseInt(st.nextToken()); // 구간의 범위

        int[] students = new int[N + 3]; // 3 ~ N+2
        Arrays.fill(students, 3, N + 3, 1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            students[Integer.parseInt(st.nextToken())] = -1;
        }


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (students[num] == -1) {
                continue;
            }
            for (int idx = num; idx < students.length; idx += num) {
                if (students[idx] != -1) {
                    students[idx] = 0;
                }
            }
        }
        
        for (int i = 3; i < students.length; i++) {
            if (students[i] == -1) {
                students[i] = 1;
            }
            students[i] += students[i - 1];
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            answer.append(students[E] - students[S - 1]).append("\n");
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}