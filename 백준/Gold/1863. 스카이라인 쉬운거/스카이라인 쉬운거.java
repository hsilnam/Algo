import java.io.*;
import java.util.*;

/*
입력
- N: 스카이라인 고도 바뀌는 지점 개수 (1<=N<=50_000)
- x,y: 스카이라인 고도 바뀌는 지점 좌표 (1<=X<=1_000_000, 0<=y<=500_000)

조건
- 스카이라인만 보고서 도시에 세워진 거물이 몇 채인지 알아내기
- 건물은 모두 직사각형
- 첫번째 지점위 X좌표는 항상 1

풀이
- stack사용
    - 건물이 낮아질때 현재 높이까지 건물수를 세어준다
    - 건물이 높아지면 stack에 넣는다
    - 0, 같은 높이 일때 stack에 넣지 않는다
    - 마지막에 처리하지 못한 건물들 (카운트 덜샌 것) 사이즈 체크해서 더해주기


출력
- 최소 건물 개수

[오답노트]
- 7% (메모리초과)
    - stack에 0, 같은 높이 같이 중복된 값이 많이 들어감
    => 0, 같은 높이 일때 stack에 넣지 않도록 수정
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            int y = Integer.parseInt(temp[1]);
            if (stack.isEmpty() && y > 0) {
                stack.push(y);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > y) { // 건물이 낮아질때는 현재 높이까지 건물 개수 세기
                stack.pop();
                cnt++;
            }
            if (!stack.isEmpty() && stack.peek() == y) { // 건물 높이가 같으면 한 건물로 취급
                continue;
            }
            if (y == 0) {
                continue;
            }
            stack.push(y);
        }
        cnt += stack.size(); // 처리하지 못한 건물들

        bw.write(Integer.toString(cnt));

        br.close();
        bw.flush();
        bw.close();
    }
}