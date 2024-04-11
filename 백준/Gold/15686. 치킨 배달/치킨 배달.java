import java.util.*;
import java.io.*;

/*
- 구현, 브루트포스
1. 입력겂을 받을 때, 집(1), 치킨집(2)의 좌표를 ArrayList로 저장한다
2. 치킨집 폐업 여부를 저장하는 boolean[] 배열을 치킨집 개수만큼 생성한다
3. 폐업시키지 않을 M개수의 치킨집의 조합을 구한다.
    1) 조합한 치킨집에 대하여 도시의 치킨 거리(각 집에서 가장 가까운 치킨집의 거리를 구하여 더해준다)를 구한다
    2) 각 조합에 대한 구해진 도시의 치킨 거리의 최소값을 구한다.
4. 최고값을 출력한다
 */
public class Main {

    public static int N, M;
    public static ArrayList<int[]> houses, stores;
    public static boolean[] opens;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        houses = new ArrayList<>();
        stores = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(temp[j]);
                if (num == 1) {
                    houses.add(new int[]{i, j});
                } else if (num == 2) {
                    stores.add(new int[]{i, j});
                }
            }
        }
        opens = new boolean[stores.size()];

        explore(0, 0);

        bw.write(Integer.toString(min));

        br.close();
        bw.close();
    }

    public static void explore(int idx, int cnt) {
        if (cnt == M) {
            int total = 0;
            for (int i = 0; i < houses.size(); i++) {
                int minDst = Integer.MAX_VALUE;
                for (int j = 0; j < stores.size(); j++) {
                    if (opens[j]) {
                        int dst = Math.abs(houses.get(i)[0] - stores.get(j)[0]) + Math.abs(houses.get(i)[1] - stores.get(j)[1]);
                        minDst = Math.min(dst, minDst);
                    }
                }
                total += minDst;
            }

            min = Math.min(total, min);
            return;
        }


        for (int i = idx; i < stores.size(); i++) {
            opens[i] = true;
            explore(i + 1, cnt + 1);
            opens[i] = false;
        }
    }
}