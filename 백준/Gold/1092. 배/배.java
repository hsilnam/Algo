import java.util.*;
import java.io.*;

/*
- 정렬 / 그리디
1. ArrayList 를 이용하여 crane, box 정보들을 저장하고 내림차순으로 정렬한다.
2. crane의 최댓값 < box의 최대값(첫번째값): 불가능한 경우로 time을 -1로한다 (모든 크레인이 해당 상자를 옮기지 못함)
3. 가능하다면, 모든 박스 최소 시간을 구한다.
    - 크레인과 박스의 루틴을 한번씩 돌려준다
        - 순차적으로 모든 크레인과 박스를 검사한다
            - 크레인 >= 박스: 옮길 수 있으므로 옮기고 박스를 없앤다
                           (boxidx가 다음 박스로 저절로 가리키므로 craneIdx만 다음으로 옮긴다)
            - 크레인 < 박스: 현재 루틴에서 해당 박스를 옮길 수 없으므로, 다음 옮길 수 있는 박스를 찾는다
        - 루틴 끝나고 시간 +1
4. 결과(최소 시간) 출력
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> cranes = new ArrayList<>();
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            cranes.add(Integer.parseInt(temp[i]));
        }

        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> boxs = new ArrayList<>();
        temp = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            boxs.add(Integer.parseInt(temp[i]));
        }

        cranes.sort(Collections.reverseOrder());
        boxs.sort(Collections.reverseOrder());

        int time = 0;
        if (cranes.get(0) < boxs.get(0)) {
            time = -1;
        } else {
            while (!boxs.isEmpty()) {
                int boxIdx = 0;
                int craneIdx = 0;
                while (craneIdx < N) {
                    if(boxIdx == boxs.size()) {
                        break;
                    }
                    if (cranes.get(craneIdx) >= boxs.get(boxIdx)) {
                        boxs.remove(boxIdx);
                        craneIdx++;
                    } else {
                        boxIdx++;
                    }
                }
                time++;
            }
        }

        bw.write(Integer.toString(time));

        br.close();
        bw.close();
    }
}