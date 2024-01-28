import java.util.*;
import java.io.*;


/*
가장 긴 증가하는 부분 수열
[방법1. dp 사용 (O(N^2) => 1초 N 1만)]
- 최대 길이를 저장하는 배열 LIS 이용 (쉽게 계산하기 위해 모든 값을 1로 초기화)
1. 입력값 순차적으로 검사
    - 처음 idx부터 현재 idx전까지의 값과 비교하여 LIS를 업데이트한다
        - 현재 idx의 값이 현재 비교하는 idx의 값보다 크고,
          현재 idx의 최대길이가 현재 비교하는 idx의 최대길이와 같거나 작으면
          => 현재 idx의 최대길이를 (현재 비교하는 idx의 최대길이 + 1)로 업데이트한다
2. 구한 LIS 중 MAX값을 구한다

[방법2. 이분탐색 사용 (O(NlogN) => 1초 N 1억)]
- 오름차순으로 정리할 리스트 list 이용
  (검사하기 쉽게 첫 값은 0으로 시작한다)
- 순차적으로 검사했을 시 기존값의 큰값 중 검사값과 가장 가까운 값을 찾아서 작은 값으로 업데이트해야 한다
  (대체될 수 있는 자리에 작은 값이 들어와야 뒤에 더 큰값들이 들어올 수 있다는 것을 이용)
  (이 때, 지금 검사하는 숫자를 리스트에서 대체할 수 있는 값을 빠르게 찾기 위해 이분탐색을 통해 검사한다)
1. 입력값 순차적으로 검사
    - (리스트의 끝값 < 현재 검사값) 이라면,
        리스트에 현재 검사값을 추가한다.
    - 아니라면,
        이분탐색을 통해 현제 검사값과 가장 가까운 큰수를 찾고, 해당 인덱스값을 현재 검사값으로 대체한다
2. 최장길이를 반환한다.
   (최장길이: 리스트의 길이 - 1 (검사를 편하게 하기위해 넣은 0값))
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] inputs = new int[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(temp[i]);
        }

//        int result = useDp(N, inputs);
        int result = useBinarySearch(N, inputs);

        bw.write(Integer.toString(result));

        br.close();
        bw.close();
    }

    public static int useDp(int N, int[] inputs) {
        int[] LIS = new int[N];
        Arrays.fill(LIS, 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (inputs[j] < inputs[i] && LIS[j] >= LIS[i]) {
                    LIS[i] = LIS[j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, LIS[i]);
        }

        return max;
    }

    public static int useBinarySearch(int N, int[] inputs) {
        List<Integer> list = new ArrayList<>();
        list.add(0);

        for (int i = 0; i < N; i++) {
            int lastData = list.get(list.size() - 1);
            if (lastData < inputs[i]) {
                list.add(inputs[i]);
                continue;
            } else if (lastData == inputs[i]) {
                continue;
            }
            // lastData > input[i]
            // binary search
            int bottom = 0;
            int top = list.size() - 1;
            while (bottom < top) {
                int mid = (bottom + top) / 2;
                if (list.get(mid) < inputs[i]) {
                    bottom = mid + 1;
                } else if (list.get(mid) > inputs[i]) {
                    top = mid;
                } else { // list.get(mid) == inputs[i]
                    top = mid;
                    break;
                }
            }
            list.set(top, inputs[i]);
        }

        return list.size() - 1;
    }

}