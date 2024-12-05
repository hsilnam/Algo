import java.io.*;
import java.util.*;

/*
입력
- N: 반의 학생 수 (3<=N<=1000)
- 1번학생부터 순차적으로 줄마다 1~5학년 때 속한 반 정보 (1<=x<=9)
    - M: 학년 (1<=M<=5, 크기 5 고정)

조건
- 임시반장: 전체 학생 중에서 같은 반이었던 학생수가 제일 많은 사람

풀이
- 같은 반이었던 학생을 저장하는 Hashset을 1차원 배열로 만든다
    - 학년별로, 각 학생이 서로 같은 반 학생인지 비교한다
- Hashset 배열를 순차적으로 비교하여 각 size(학생수)의 최대값을 비교한다
    이때, 같은 수더라도 뒤에 오는 학생(학생 번호가 높은 사람)은 업데이트하지 않는다 (작은 번호를 출력해야하므로)

출력
- 임시 반장으로 정해진 학생의 번호 출력
- 단, 임시반장이 될 수 있는 학생 여러명 -> 그 중에서 가장 작은 번호로 출력
    => 같은 반 학생 수 내림차순, 학생 번호 올림차순
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(br.readLine());

        int[][] students = new int[N + 1][5]; // 학생, 학년 , 학생 번호 맞춰주기
        for (int i = 1; i < N + 1; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                students[i][j] = Integer.parseInt(temp[j]);
            }
        }

        HashSet<Integer>[] sameClass = new HashSet[N + 1];
        for (int i = 1; i < N + 1; i++) {
            sameClass[i] = new HashSet<>();
        }

        for (int i = 1; i < N + 1; i++) { // 비교할 학생 번호 기준
            for (int j = 0; j < 5; j++) { // 학년
                for (int i2 = 1; i2 < N + 1; i2++) { // 비교할 학생 번호
                    if (students[i][j] == students[i2][j] && i != j) {
                        sameClass[i].add(i2);
                        sameClass[i2].add(i);
                    }
                }
            }
        }

        int maxSize = 0;
        int answer = 0;
        for (int i = 1; i < N + 1; i++) {
            if (maxSize < sameClass[i].size()) { // 클때만, 같은게 있더라도 작은 번호만 업데이트 되게
                maxSize = sameClass[i].size();
                answer = i;
            }
        }

        bw.write(Integer.toString(answer));

        br.close();
        bw.flush();
        bw.close();
    }

}