/*
[입력]
- 준현이와 성민이에게 주어진 현금
- 2021년 1월 1일부터 2021년 1월 14일까지의 MachineDuck 주가가 공백을 두고 차례대로 주어진다 (모든 입력은 1000 이하의 양의 정수)
[조건]
- 준현이 매매법
    - 준현이는 주식을 살 수 있다면 무조건 최대한 많이 산다
    - 주식을 살 수 있다면 가능한 만큼 즉시 매수
- 성민이 메메법
    - 모든 거래는 전량 매수와 전량 매도로 이루어짐
        - 현재 가지고 있는 현금이 100원이고 주가가 11원이라면 99원어치의 주식을 매수하는 것이다
        - 단, 현금이 100원 있고 주가가 101원이라면 주식을 살 수 없다
        - 성민이는 빚을 내서 주식을 하지는 않는다
    - 3일 연속 가격이 전일 대비 상승하는 주식은 다음날 무조건 가격이 하락한다고 가정
        - 따라서 현재 소유한 주식의 가격이 3일째 상승한다면, 전량 매도한다
        - 전일과 오늘의 주가가 동일하다면 가격이 상승한 것이 아니다.
    - 3일 연속 가격이 전일 대비 하락하는 주식은 다음날 무조건 가격이 상승한다고 가정한다
        - 따라서 이러한 경향이 나타나면 즉시 주식을 전량 매수한다. 전일과 오늘의 주가가 동일하다면 가격이 하락한 것이 아니다.

- 오로지 MachineDuck이라는 기업의 주식만 거래가 가능
- 내기 기간은 2021년 1월 1일부터 2021년 1월 14일까지
- 준현이와 성민이에게 주어진 현금은 동일
- 세기의 대결이기 때문에 이 기간에는 매일 주식 거래가 가능
- 2021년 1월 14일에 더 많은 자산을 보유한 사람이 승리
- 1월 14일의 자산은 (현금 + 1월 14일의 주가 × 주식 수)

[출력]
1월 14일 기준
    - 준현이의 자산이 더 크다면 "BNP"
    - 성민이의 자산이 더 크다면 "TIMING"
    - 둘의 자산이 같다면 "SAMESAME"
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int totalDays = 14;
    static int[] infos;

    static Asset jhAsset;
    static Asset smAsset;


    public static class Asset {
        int price;
        int num;

        public Asset(int price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Asset{" +
                    "price=" + price +
                    ", num=" + num +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalPrice = Integer.parseInt(br.readLine());
        jhAsset = new Asset(totalPrice);
        smAsset = new Asset(totalPrice);

        infos = new int[totalDays];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < totalDays; i++) {
            infos[i] = Integer.parseInt(temp[i]);
        }

        jhAlgo();
        smAlgo();

        int jhResult = calcAsset(jhAsset, infos[totalDays - 1]);
        int smResult = calcAsset(smAsset, infos[totalDays - 1]);

//        System.out.println(jhResult);
//        System.out.println(smResult);
        if (jhResult > smResult) {
            System.out.println("BNP");
        } else if (jhResult < smResult) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }
    }

    public static void jhAlgo() {
        for (int info : infos) {
            allBuy(jhAsset, info);
        }
    }

    public static void smAlgo() {
        int prev = infos[0], cur;
        int trend = 0;
        for (int i = 1; i < totalDays; i++) {
            cur = infos[i];
            if (prev < cur) {
                if (trend < 0) {
                    trend = 1;
                } else {
                    trend += 1;
                }
            } else if (prev > cur) {
                if (trend > 0) {
                    trend = -1;
                } else {
                    trend -= 1;
                }
            }

//            System.out.println(trend);
//            System.out.println(smAsset);
            if (trend >= 3) {
                allSell(smAsset, cur);
//                System.out.println(smAsset);
            } else if (trend <= -3) {
                allBuy(smAsset, cur);
//                System.out.println(smAsset);
            }

            prev = cur;
        }
    }

    public static Asset allBuy(Asset tgAsset, int info) {
        if (tgAsset.price < info) {
            return tgAsset;
        }
        int num = tgAsset.price / info;
        tgAsset.price -= num * info;
        tgAsset.num += num;
        return tgAsset;
    }

    public static Asset allSell(Asset tgAsset, int info) {
        tgAsset.price += tgAsset.num * info;
        tgAsset.num = 0;
        return tgAsset;
    }

    public static int calcAsset(Asset tgAsset, int info) {
        return tgAsset.price + info * tgAsset.num;
    }
}