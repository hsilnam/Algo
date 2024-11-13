import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
ㄱ자 모양으로 생긴 밭의 너비 구하기
- 전체너비에서 빈공간의 너비를 제외한다
- 최대 길이의 양 옆의 높이차가 빈 공간의 사각형의 길이 이다
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());
        int maxWIdx = -1;
        int maxHIdx = -1;
        int[] lens = new int[6];
        for (int i = 0; i < 6; i++) {
            String[] temp = br.readLine().split(" ");
            int d = Integer.parseInt(temp[0]);
            int len = Integer.parseInt(temp[1]);
            lens[i] = len;

            if (d == 1 || d == 2) {
                if(maxWIdx == -1) {
                    maxWIdx = i;
                    continue;
                }
                if (lens[maxWIdx] < len) {
                    maxWIdx = i;
                }
            }
            if (d == 3 || d == 4) {
                if(maxHIdx == -1) {
                    maxHIdx = i;
                    continue;
                }
                if (lens[maxHIdx] < len) {
                    maxHIdx = i;
                }
            }
        }

        int blankW = Math.abs(lens[(maxHIdx + 1) % 6] - lens[(maxHIdx + 5) % 6]);
        int blankH = Math.abs(lens[(maxWIdx + 1) % 6] - lens[(maxWIdx + 5) % 6]);
        
        int result = ((lens[maxWIdx] * lens[maxHIdx]) - (blankW * blankH)) * K;

        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }
}