import java.io.*;
import java.util.*;

/*
입력
- N: daldidalgo의 횟수(1<=N<=10^9)

조건
- 작업 둘 중에 하나 선택
    - a-z 중에서 원하는 알파벳 하나 골라 지금까지 입력한 내용의 맨 뒤에 입력
    - 지금까지 입력한 문자열의 연속된 부분 문자열 복사후, 입력한 내용의 맨 뒤에 붙여 넣음

풀이
- 곂치는 것 찾기 그 외는 꼭 일일히 입력해야하는 것들
    ex)
    N=2
    daldi (5) + dal(1) +go(2)
    daldidalgo (1)
    daldida(1) + n(1)
    => 11

    N=3
    daldi (5) + dal(1) +go(2)
    daldidalgo (1)
    daldidalgo
    daldida(1) + n(1)
    => 11

    N=4
    daldi (5) + dal(1) +go(2)
    daldidalgo (1)
    daldidalgo
    daldidalgo(1)
    daldida(1) + n(1)
    => 12
    ...
    N=6
    daldi (5) + dal(1) +go(2)
    daldidalgo (1)
    daldidalgo
    daldidalgo(1)
    daldidalgo
    daldidalgo
    daldida(1) + n(1)
    => 12
    - 처음과 끝 초수는 동일: 8+2 = 10
    - daldidalgo를 2^i당 초가 하나씩 오름
                   (여기서 숫자는 묶음)
        => floor(기본(10) + i)를 구하면 답이 나온다
           but, 큰수에 대한 부동소수점, 정밀도 문제 때문에 답이 나오지 않음
       -> N/2를 해가면서 시간을 count한다

출력
- 언급된 시행 중 선택하여 매초 시행했을 때, N번의 daldidalgo를 입력한 후 1번의 daldidan을 입력할 수 있는 최소 시간
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Long N = Long.parseLong(br.readLine());

        int time = 10; // basic
        while (N > 1) {
            N /= 2;
            time++;
        }

        bw.write(Integer.toString(time));

        br.close();
        bw.flush();
        bw.close();
    }

}