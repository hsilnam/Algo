import java.io.*;
import java.util.*;

/*
입력
- Sl, Sr: 두 알파벳 소문자 처음 위치 (왼, 오)
- 알파벳 소문자로 구성된 문자열 (최대 100자)
    - 빈문자열 없음

조건
- 독수리 타법
- 입력
    - 좌음자판: 왼손
    - 모음자판: 오른손
- 시간
    - 이동시간: |x1-x2| + |y1-y2|
    - 키 누르는 시간: 1
- 두손을 동시에 움직일 수 없음
- 쿼타식 키보드

풀이
- 알파벳의 좌표, 손 기준을 Hashmap으로 저장하기 (저장은 영어로)
    - 0은 왼손, 1은 오른손
- 현재 왼손, 오른손 위치 알파벳으로 저장하기
- 만약 현재 찾으려는 알파벳이 현재 손위치와 같으면 이동 없이 누르기, 다르면 이동하고 누르기

출력
- 문자열 출력하는데 걸리는 최소 시간

 */

public class Main {

    public static class AlphaInfo {
        int x;
        int y;
        Hand hand;

        public AlphaInfo(int x, int y, Hand hand) {
            this.x = x;
            this.y = y;
            this.hand = hand;
        }
    }

    public enum Hand {
        LEFT(0), RIGHT(1);

        private final int value;

        Hand(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public final static HashMap<Character, AlphaInfo> alphaInfoMap
            = new HashMap<>() {{
        put('q', new AlphaInfo(0, 0, Hand.LEFT));
        put('w', new AlphaInfo(0, 1, Hand.LEFT));
        put('e', new AlphaInfo(0, 2, Hand.LEFT));
        put('r', new AlphaInfo(0, 3, Hand.LEFT));
        put('t', new AlphaInfo(0, 4, Hand.LEFT));
        put('y', new AlphaInfo(0, 5, Hand.RIGHT));
        put('u', new AlphaInfo(0, 6, Hand.RIGHT));
        put('i', new AlphaInfo(0, 7, Hand.RIGHT));
        put('o', new AlphaInfo(0, 8, Hand.RIGHT));
        put('p', new AlphaInfo(0, 9, Hand.RIGHT));

        put('a', new AlphaInfo(1, 0, Hand.LEFT));
        put('s', new AlphaInfo(1, 1, Hand.LEFT));
        put('d', new AlphaInfo(1, 2, Hand.LEFT));
        put('f', new AlphaInfo(1, 3, Hand.LEFT));
        put('g', new AlphaInfo(1, 4, Hand.LEFT));
        put('h', new AlphaInfo(1, 5, Hand.RIGHT));
        put('j', new AlphaInfo(1, 6, Hand.RIGHT));
        put('k', new AlphaInfo(1, 7, Hand.RIGHT));
        put('l', new AlphaInfo(1, 8, Hand.RIGHT));

        put('z', new AlphaInfo(2, 0, Hand.LEFT));
        put('x', new AlphaInfo(2, 1, Hand.LEFT));
        put('c', new AlphaInfo(2, 2, Hand.LEFT));
        put('v', new AlphaInfo(2, 3, Hand.LEFT));
        put('b', new AlphaInfo(2, 4, Hand.RIGHT));
        put('n', new AlphaInfo(2, 5, Hand.RIGHT));
        put('m', new AlphaInfo(2, 6, Hand.RIGHT));
    }};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] hands = new char[]{st.nextToken().charAt(0), st.nextToken().charAt(0)}; // left, right

        int cnt = 0;
        for (char target : br.readLine().toCharArray()) {
            AlphaInfo targetInfo = alphaInfoMap.get(target);

            int handIdx = targetInfo.hand.getValue();
            char hand = hands[handIdx];
            
            cnt++; // press time
            if (hand == target) {
                continue;
            }
            AlphaInfo handInfo = alphaInfoMap.get(hand);
            cnt += Math.abs(handInfo.x - targetInfo.x) + Math.abs(handInfo.y - targetInfo.y);

            hands[handIdx] = target;
        }

        bw.write(String.valueOf(cnt));

        br.close();
        bw.flush();
        bw.close();
    }

}