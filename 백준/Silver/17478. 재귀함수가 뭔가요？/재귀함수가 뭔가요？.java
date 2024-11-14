import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/*
- 재귀함수
    - 종료조건: N만큼 반복했을 때
    - 앞에 반복횟수만큼 tab하기
    - 마지막에(N만큼 반복했을 때) 정확한 답을 해줄 것
    - 앞과 뒤에 시작, 마지막 멘트 달아놓을 것
- StringBuilder 사용
 */
public class Main {

    public static final String TAB = "____";
    public static int N;
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        N = Integer.parseInt(br.readLine());
        answer.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        bot(0);
        answer.append("라고 답변하였지.\n");


        bw.write(answer.toString());

        br.close();
        bw.close();
    }

    public static void bot(int cnt) {
        StringBuilder offset = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            offset.append(TAB);
        }

        answer.append(offset)
                .append("\"재귀함수가 뭔가요?\"\n");
        if(cnt == N) {
            answer.append(offset)
                    .append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
            return;
        }

        answer.append(offset)
                .append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n")
                .append(offset)
                .append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n")
                .append(offset)
                .append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
        bot(cnt+1);
        answer.append(offset).append(TAB) // 다음 반복횟수와 라인이 맞아야하므로 붙어야하므로 tab하나 더 추가
                .append("라고 답변하였지.\n");
    }
}