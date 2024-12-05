import java.io.*;
import java.util.*;

/*
입력
- 스위치 개수 (100이하 양의 정수)
- 스위치 상태: 켜져있음 1, 꺼져있음 0
- 학생 수 (100이하 양의 정수)
- 학생 성별, 학생 받는 스위치 수
    - 남학생 1, 여학생 2
    - 스위치 개수 이하 양의 정수

조건
- 남학생: 스위치 번호가 자기가 받은 수의 배수 -> 스위치 상태 바꾸기
    (3번 -> 3,6 스위치 상태 바꾸기)
- 여학생: 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로
        좌우 대칭이면서 가장 많은 스위치 포함하는 구간 찾아서
        그 구간에 속한 스위치 상태 모두 바꾸기
        - 이때, 구간에 속한 스위치 개수는 항상 홀수
- 입력되는 순서대로 스위치 조작

풀이
- 스위치 상태 정보 배열에 저장
- 남학생이면, 전체 스위치 범위를 넘지 않으면서, 받은 수의 배수들 상태 변경
- 여학생이면, 전체 스위치 범위를 넘지 않으면서, 받은 수의 양옆값을 순차적으로 검사하면서
    값이 같은 경우에만 상태 변경
- 출력할 시 스위치 개수 20줄 넘어가면 들여쓰기

출력
- 스위치 상태 1번부터 마지막 스위치까지 한줄에 20개씩 출력
- 켜진 스위치 1, 꺼진 스위치 0


[참고]
- StringBuilder가 ArrayList보다 문자열 조작에 더 적합해 성능이 뛰어나다
- Collections.reverse(), Collections.reverseOrder()의 차이
    - Collections.reverse(): 단순히 역순 정렬
    - Collections.reverseOrder(): 내림차순 정렬
 */

public class Main {
    public static int[] switchs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 스위치 개수
        String[] temp = br.readLine().split(" ");
        switchs = new int[N + 1]; // 번호 맞춰주기 위해 앞에 패딩
        for (int i = 1; i < N + 1; i++) {
            switchs[i] = Integer.parseInt(temp[i - 1]);
        }

        int M = Integer.parseInt(br.readLine()); // 학생 개수

        // 순차적으로 조작
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            String gender = temp[0];
            int num = Integer.parseInt(temp[1]);

            if (gender.equals("1")) { // 남학생
                for (int j = num; j < N + 1; j += num) { //  배수만큼 조작
                    click(j);
                }
            } else { // 여학생
                click(num); // 자기자신
                for (int j = 1; j < (N + 1) / 2; j++) {
                    int leftIdx = num - j;
                    int rightIdx = num + j;
                    if (leftIdx < 1 || rightIdx > N) { //범위 넝은 경우
                        break;
                    }
                    if (switchs[leftIdx] != switchs[rightIdx]) { // 대칭이 이루어지지 않는 경우
                        break;
                    }
                    //대칭
                    click(leftIdx);
                    click(rightIdx);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            answer.append(switchs[i]).append(" ");
            if (i % 20 == 0) {
                answer.append("\n");
            }
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void click(int idx) {
        switchs[idx] ^= 1; // XOR 비트연산
    }
}