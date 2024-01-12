import java.util.*;
import java.io.*;

/*
1. HashMap<Integer, HashTree<Integer>>으로 클로버의 X축 선상의 Y값들을 저장한다. (오름차순 자동정렬)
2. HashMap<Integer, HashTree<Integer>>으로 클로버의 Y축 선상의 X값들을 저장한다. (오름차순 자동정렬)
3. HashTree의 higher, lower를 통해 가까운 갑승ㄹ 구함

배운 것
-----------
- TreeSet
    - Red-black으로 구현
        - 데이터 값에 의한 정렬
        - 삽입, 삭제, 탐색: logN
    - 중복 X

실패들
-----------
- 2초 (시간초과)
    1. HashMap<Integer, ArrayList<Integer>>으로 클로버의 X축 선상의 Y값들을 저장한 후, Y값들을 오름차순으로 정렬한다 (NlogN)
    2. HashMap<Integer, ArrayList<Integer>>으로 클로버의 Y축 선상의 X값들을 저장한 후, X값들을 오름차순으로 정렬한다 (NlogN)
    3. 이동할때마다 이분탐색을 통해 제일 가까운 값을 구한다 (M * logN))
    => N * logN
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        Map<Integer, TreeSet<Integer>> luckyXs = new HashMap<>();
        Map<Integer, TreeSet<Integer>> luckyYs = new HashMap<>();
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);

            if (!luckyXs.containsKey(x)) {
                luckyXs.put(x, new TreeSet<>());
            }
            luckyXs.get(x).add(y);

            if (!luckyYs.containsKey(y)) {
                luckyYs.put(y, new TreeSet<>());
            }
            luckyYs.get(y).add(x);
        }

        temp = br.readLine().split("");
        int x = 0, y = 0;
        for (int i = 0; i < M; i++) {
            if (temp[i].equals("L")) {
                x = luckyYs.get(y).lower(x);
            } else if (temp[i].equals("R")) {
                x = luckyYs.get(y).higher(x);
            } else if (temp[i].equals("U")) {
                y = luckyXs.get(x).higher(y);
            } else if (temp[i].equals("D")) {
                y = luckyXs.get(x).lower(y);
            }
        }
        bw.write(x + " " + y);

        br.close();
        bw.close();
    }
}