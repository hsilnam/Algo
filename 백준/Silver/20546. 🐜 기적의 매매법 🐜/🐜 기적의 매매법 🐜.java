import java.io.*;
import java.util.*;

/*
입력
- 준현, 성민에게 주어진 현금
- 2021.1.1 ~ 2021.1.14 MachineDuck 주가
- 모든 입력 1000이하의 양의 정수

조건
- 1월 14일 자산: 현금 + 1월 14일의 주가 × 주식 수
- 준현: BNP
    - 주식 팔지 않음
    - 주식을 살 수 있다면 가능한 만큼 즉시 매수
- 성민: TIMING. 33 매매법
    - 모든 거래는 전량 매수와 전량 매도
        - 성민이는 빚을 내서 주식을 하지는 않음
    - 3일 연속 가격이 전일 대비 상승하는 주식은 다음날 무조건 가격이 하락
        - 따라서 현재 소유한 주식의 가격이 3일째 상승한다면, 전량 매도한다
        - 전일과 오늘의 주가가 동일하다면 가격이 상승한 것이 아님
    - 3일 연속 가격이 전일 대비 하락하는 주식은 다음날 무조건 가격이 상승한다
        - 따라서 이러한 경향이 나타나면 즉시 주식을 전량 매
         - 전일과 오늘의 주가가 동일하다면 가격이 하락한 것이 아님

풀이
- 이차원 배열로 남아있는 자산과, 주식 개수를 저장한다
- 마지막 14일을 제외하고 각 매매법 알고리즘에 따라 자산과 개수를 저장한다
    - BNP: 무조건 주식 살 수 있으면 산다
    - TIMING
        - 트랜드를 분석하고 동일 트랜드의 개수를 센다
            - 트랜드 분석: 전 주식과 현재 주식 비교 했을 때, 상승세인지 하락세인지 비교 (양수, 음수로 비교)
                - 동일한 것은 취급하지 않는다 (0)
            - 트랜드가 같으면 트랜드 개수를 1 늘린다
            - 트랜드가 다르면 트랜드 개수를 1(상승세),-1(하락세)로 초기화한다
        - 3일연속, 상승/하락세이면 전부 주식 팔기/사기를 수행한다
- 마지막 14일에 최종 자산을 계산한다 (현금 + 1월 14일의 주가 × 주식 수)
- 둘의 결과를 비교해서 답을 출력한다

출력
- 1월 14일 기준
    - 준현 자산이 더크면 "BNP"
    - 성민 자산이 더크면 "TIMING"
    - 둘의 자산이 같다면 "SAMESAME"

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] AInfo = new int[2], BInfo = new int[2]; // asset, cnt
        AInfo[0] = BInfo[0] = Integer.parseInt(br.readLine()); // 준현, 성민


        int trendCnt = 0; // up:+,down:-
        int prevStock = -1;
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < 13; i++) { // 14일 제외
            int stock = Integer.parseInt(temp[i]);
            // A Algo
            allBuy(AInfo, stock);

            // B Algo
            if (prevStock == -1) {
                prevStock = stock; // next
                continue;
            }

            int trend = 0; // up:1, down:-1, none:0
            if (prevStock < stock) { // 상승세
                trend = 1;
            } else if (prevStock > stock) { // 하락세
                trend = -1;
            }


            if ((trend < 0 && trendCnt > 0) ||
                    (trend > 0 && trendCnt < 0)) { // 트랜드가 다른 경우
                trendCnt = trend; // 트랜드 초기화
                prevStock = stock; // next
                continue;
            }

            // 트랜드가 같은 경우
            trendCnt += trend;
            if (trendCnt >= 3) { // 3일 이상 상승세
                allSell(BInfo, stock);
            } else if (trendCnt <= -3) { // 3일 이하 하락세
                allBuy(BInfo, stock);
            }
            prevStock = stock; // next
        }

        // 최종 14일 calc
        int AResult = calcResult(AInfo, Integer.parseInt(temp[13]));
        int BResult = calcResult(BInfo, Integer.parseInt(temp[13]));

        String answer = "SAMESAME";
        if (AResult > BResult) {
            answer = "BNP";
        } else if (AResult < BResult) {
            answer = "TIMING";
        }

        bw.write(answer.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static int calcResult(int[] info, int stock) {
        return info[0] + stock * info[1];
    }

    public static void allBuy(int[] info, int stock) {
        if (stock > info[0]) {
            return;
        }
        int cnt = info[0] / stock;
        info[0] -= cnt * stock;
        info[1] += cnt;
    }

    public static void allSell(int[] info, int stock) {
        info[0] += info[1] * stock;
        info[1] = 0;
    }
}