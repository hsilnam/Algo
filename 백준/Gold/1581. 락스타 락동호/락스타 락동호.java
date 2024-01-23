import java.util.*;
import java.io.*;

/*
다음과 같은 패턴으로 숫자를 센다
{FF ... FF} {FS} {SS ... SS} {SF FS ... SF FS} {SF}
각 FF, FS, SF, SS 별로 사용 가능한 개수 정보를 가진 nums배열과 마지막이 F인지 S인지 구분하는 isEndF을 사용한다
각 조건에 들어갔을 때마다 남은 갯수 정보 nums와 isEndF 정보를 알맞게 업데이트해준다.
1. FF가 하나 이상인 경우
    - 전부 사용한다
    - 끝은 F라는 것을 표시한다
2. FS가 하나 이상인 경우
    - S로 변환하기 위한 발판으로 1개 사용한다
    - 끝은 S라는 것을 표시한다
3. SS가 하나 이상이고 현재 끝이 S인 경우
    - F로 끝난 상태이면 S를 붙이지 못하기 때문에 끝이 S일 경우에만 가능하다
    - 전부 사용한다
    - 끝은 S라는 것을 표시한다
4. SF가 하나 이상이고 현재 끝이 S인 경우
    - F로 끝난 상태이면 S를 붙이지 못하기 때문에 끝이 S일 경우에만 가능하다
    - SF FS를 쌍으로 묶어서 계산하기 위해 쌍을 묶을 수 있는 min값을 구하고 min*2로 가능한 쌍의 전체 개수를 구해 더한다
    - 끝이 전부 S로 끝나기 때문에 SF를 하나 더 붙일 수 있다. SF가 하나 이상이면 1개 사용한다
5. 이렇게 사용한 전체 개수를 출력한다 
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        int[] nums = new int[4]; // FF, FS, SF, SS
        for (int i = 0; i < 4; i++) {
            nums[i] = Integer.parseInt(temp[i]);
        }

        boolean isEndF = false; // F로 끝나는 경우, true: F, false: S
        int total = 0;
        if (nums[0] > 0) { // FF ... FF
            total += nums[0];
            nums[0] = 0;
            isEndF = true;
        }
        if (nums[1] > 0) { // FS
            total += 1;
            nums[1] -= 1;
            isEndF = false;
        }
        if (!isEndF && nums[3] > 0) { // SS ... SS
            total += nums[3];
            nums[3] = 0;
            isEndF = false;
        }

        if (!isEndF) {
            int min = Math.min(nums[1], nums[2]); // SF FS ... SF FS
            total += min * 2;
            nums[1] -= min;
            nums[2] -= min;

            if (nums[2] > 0) { // SF
                total += 1;
                nums[2] -= 1;
            }
            isEndF = false;
        }

        bw.write(Integer.toString(total));

        br.close();
        bw.close();
    }
}