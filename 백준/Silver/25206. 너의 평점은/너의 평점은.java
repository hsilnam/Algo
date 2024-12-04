import java.io.*;
import java.util.*;

/*
입력
- 20줄에 걸쳐 수강한 전공과목의 과목명, 학점, 등급
    - 과목명
        - 1<=과목명<=50
        - 알파벳 대소문자, 숫자, 띄어쓰기 없음
        - 모든 과목명은 서로 다르다
    - 학점
        -1.0,2.0,3.0,4.0 중 하나
    - 등급
        - A+,A0,B+,B0,C+,C0,D+,D0,F,P
        - 적어도 한 과목은 등급이 P가 아님 보장
조건
- 전공평점: 전공과목별(학점*과목평점)의 합 /학점의 총합
- P인 과목은 계산에서 제외

풀이
- double 사용
- 전공평점을 구하기 위해 입력값을 받을 떄 계산한다
- Hashmap을 통해 등급에 따른 과목평점을 정리한다

출력
- 전공평점 출력
- 절대오차(상대오차) 10^-4이하면 정답으로 인정
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, Double> map = new HashMap<>();
        map.put("A+", 4.5);
        map.put("A0", 4.0);
        map.put("B+", 3.5);
        map.put("B0", 3.0);
        map.put("C+", 2.5);
        map.put("C0", 2.0);
        map.put("D+", 1.5);
        map.put("D0", 1.0);
        map.put("F", 0.0);

        double calc = 0;
        double gradeSum = 0;
        for (int i = 0; i < 20; i++) {
            String[] temp = br.readLine().split(" ");
            double grade = Double.parseDouble(temp[1]);
            String gp = temp[2];
            if (gp.equals("P")) {
                continue;
            }
            double gradePoint = map.get(gp);
            calc += (grade * gradePoint);
            gradeSum += grade;
        }

        double result = calc / gradeSum;
        bw.write(Double.toString(result));

        br.close();
        bw.flush();
        bw.close();
    }

}