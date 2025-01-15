import java.io.*;
import java.util.*;

/*
입력
- N: 숫자 카드의 개수 (1 <= N <= 500_000)
- 숫자카드의 숫자: -10_000_000<=숫자<=10_000_000
    - 같은 수 없음
- M: 가지고 있는 숫자 카드인지 체크하는 개수 (1<=M<=500_000)
    - 숫자카드의 숫자: -10_000_000<=숫자<=10_000_000


풀이
- 가지고 있는 숫자카드들을 정렬해서 배열로 가지고 있는다
- 이분 탐색을 통해서 같은 숫자가 있는지 좁혀나간다

출력
- 가지고 있으면1, 아니면 0

 */

public class Main {
    public static int[] cards;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        cards = new int[N];

        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            answer.append(isContains(Integer.parseInt(st.nextToken()))).append(" ");
        }


        bw.write(answer.toString());

        br.readLine();
        bw.flush();
        bw.close();
    }

    public static int isContains(int num) {
        int left = 0, right = cards.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (cards[mid] == num) {
                return 1;
            } else if (cards[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }

}