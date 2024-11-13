import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

/*
- x번 사람의 y번쨰 일의 예상일량: WORKxy = (i:1~5) Axi * Biy의 합
- x가 한 일의 총량: (i: 1~5) WORKix의 합
- 각 사람들의 번호 1~5 (Inseo, Junsuk, Jungwoo, Jinwoo, Youngki)이지만
    가장 일이 바쁘지 않은 사람을 구할때 값이 같을 시에는 역의 순서로 앞에있는 것을 가져오기 때문에
    (Youngki, Jinwoo, Jungwoo, Junsuk, Inseo)
    뒤에서(5번부터) 최소값을 비교한다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] map1 = new int[5][5];
        int[][] map2 = new int[5][5];

        for (int i = 0; i < 5; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                map1[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for (int i = 0; i < 5; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                map2[i][j] = Integer.parseInt(temp[j]);
            }
        }


        int[] works = new int[5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                int sum = 0;
                for (int i = 0; i < 5; i++) {
                    sum += map1[x][i] * map2[i][y];
                }
                works[x] += sum;
            }
        }

        String[] names = {"Inseo", "Junsuk", "Jungwoo", "Jinwoo", "Youngki"};
        int idx = 5;
        int min = Integer.MAX_VALUE;
        for (int i = 4; i >= 0; i--) {
            if (min > works[i]) {
                idx = i;
                min = works[i];
            }
        }
        bw.write(names[idx]);

        br.close();
        bw.close();
    }
}