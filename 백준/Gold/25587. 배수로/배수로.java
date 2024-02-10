import java.util.*;
import java.io.*;


/*
- union find를 이용하여 그룹 나누기
- HashMap을 이용해서 그룹 별로 총 용량, 강수량, 도시 개수 구하기
- 도시 넘버는 1부터 시작하도록 맞춰주기
- union할 시 전체 물에 잠긴 도시의 개수를 구한다
    - (초기 물에 잠긴 도시의 개수는 input 받을 때 구함)
    - 합쳐지면서 물에 잠기지 않게 되거나, 물에 잠긴 도시의 개수가 중복으로 더해질 위험이 있으므로,
       합치는 두 도시에 대하여 다시 한번 더 물에 잠겼는지 체크하여 해당 도시의 수를 빼고 시작한다ㄴ

[실패들]
- 2% (시간초과)
    - 물에 잠긴 도시 개수을 input '2'일 때마다 체크하여 구함
 */

public class Main {
    public static int[] parents;
    public static HashMap<Integer, int[]> groupInfo; // citynum, [cap, rain, cityCnt]
    public static int totalCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        make(N);
        groupInfo = new HashMap<>();
        String[] tempCaps = br.readLine().split(" ");
        String[] tempRains = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int cap = Integer.parseInt(tempCaps[i]);
            int rain = Integer.parseInt(tempRains[i]);
            groupInfo.put(i + 1, new int[]{cap, rain, 1});
            if (cap < rain) {
                totalCnt += 1;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            if (temp[0].equals("2")) {
                result.append(totalCnt).append("\n");
            } else { // 1
                union(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            }
        }

        bw.write(result.toString());

        br.close();
        bw.close();
    }

    public static void make(int size) {
        parents = new int[size + 1];
        for (int i = 1; i < size + 1; i++) {
            parents[i] = i;
        }
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            parents[pb] = pa;

            int[] paGroupInfo = groupInfo.get(pa);
            int[] pbGroupInfo = groupInfo.get(pb);

            int cap = paGroupInfo[0] + pbGroupInfo[0];
            int rain = paGroupInfo[1] + pbGroupInfo[1];
            int cityNum = paGroupInfo[2] + pbGroupInfo[2];
            groupInfo.put(pa, new int[]{cap, rain, cityNum});
            groupInfo.remove(pb);

            if(paGroupInfo[0] < paGroupInfo[1]) {
                totalCnt -= paGroupInfo[2];
            }
            if(pbGroupInfo[0] < pbGroupInfo[1]) {
                totalCnt -= pbGroupInfo[2];
            }
            if (cap < rain) {
                totalCnt += cityNum;
            }
        }
    }

    public static int find(int n) {
        if (parents[n] == n) {
            return parents[n];
        } else {
            return parents[n] = find(parents[n]);
        }
    }
}