import java.io.*;
import java.util.*;

/*
입력
- N: 거스름돈 액수(1<=N<=100_000)

조건
- 2원, 5원으로만 거스롬돈 (무한대)

풀이
- 최소가 되려면 2원보다 큰 5원의 비율을 최대한 많이 가져가야한다
- 5로 최대한 거스름돈을 주고 나머지를 2원으로 주어야한다
- 만약 나누어 떨어지지 않는다면, 5원을 하나 줄였을 때의 2원 거스름돈을 구해야한다
- 5원을 전부 빼고 2원만 이루어져도 거스름돈을 주지 못한다면 -1을 출력한다

출력
- 거스름돈 동전의 최소 개수
    거슬러 줄 수 없으면 -1 출력
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int coinCnt = 0;
        for (int i = 0; i <= N / 5; i++) {// 5원 하나씩 빼보기
            coinCnt = N / 5 - i;
            int remain = N % 5 + 5 * i; // 5원으로 채우고 남은 거스름돈
            if (remain == 0) { // 5원만으로 완성
                break;
            }
            if (remain % 2 == 0) { // 2원으로도 완성되면
                coinCnt += remain / 2;
                break;
            }
        }

        bw.write(Integer.toString((coinCnt > 0) ? coinCnt : -1));

        br.close();
        bw.flush();
        bw.close();
    }

}