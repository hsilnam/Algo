import java.util.*;
import java.io.*;


/*
(큰 숫자 순으로 반복 검사)
해당 숫자가 해당 위치(index)에 있는가?
1. 있다면 pass
2. 없다면,
    1) 해당 숫자 맨 앞으로 오게하기
    2) 해당 위치로 옮기기

 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int t = 0; t < T; t++) {
            // get input
            String[] temp = br.readLine().split(" ");
            int N = Integer.parseInt(temp[0]);
            int[] arr = new int[N + 1]; // 0은 index와 숫자 편하게 맞춰주려고 함
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(temp[i]);
            }

            // check
            ArrayList<Integer> filps = new ArrayList<>();
            for (int i = N; i >= 2; i--) { // 첫번째는 바꿀 필요없음
                if (arr[i] == i) {
                    continue;
                }
                // 해당 숫자 현재 위치를 찾아서 앞으로 가져오기
                int idx = 0;
                for (int j = 1; j <= N; j++) {
                    if (arr[j] == i) {
                        idx = j;
                        break;
                    }
                }
                if(idx != 1) { // 첫번째는 바꿀 필요없음
                    filps.add(idx);
                    filp(arr, idx);
                }

                // 앞으로 가져온 큰 수, 해당 위치로 옮기기
                filps.add(i);
                filp(arr, i);
            }

            result.append(filps.size());
            for (int f : filps) {
                result.append(" ").append(f);
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }

    static public void filp(int[] arr, int idx) {
        int left = 1;
        int right = idx;
        while (left < right) {
            int temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
            left++;
            right--;
        }
    }
}