import java.util.*;
import java.io.*;


// stack --> O(1)
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split("");
        Stack<String> left = new Stack<>();
        Stack<String> right = new Stack<>();
        for (String s : temp) {
            left.push(s);
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "L":
                    if(!left.isEmpty()) {
                        right.push(left.pop());
                    }
                    break;
                case "D":
                    if(!right.isEmpty()) {
                        left.push(right.pop());
                    }
                    break;
                case "B":
                    if(!left.isEmpty()) {
                        left.pop();
                    }
                    break;
                case "P":
                    left.push(command[1]);
                    break;
            }
        }
        StringBuilder result = new StringBuilder();
        for (String l : left) {
            result.append(l);
        }
        while (!right.isEmpty()){
            result.append(right.pop());
        }

        System.out.println(result.toString());
    }
}

/*

// old solve --> 시간초과
// ArrayList -> O(N)
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split("");
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(""); // 앞 공백
        for (String s : temp) {
            inputs.add(s);
        }
        inputs.add(""); // 뒤 공백
        int N = Integer.parseInt(br.readLine());

        int curIdx = inputs.size()-1;
        for (int i = 0; i < N; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "L":
                    if (curIdx > 0) {
                        curIdx -= 1;
                    }
                    break;
                case "D":
                    if (curIdx < inputs.size()) {
                        curIdx += 1;
                    }
                    break;
                case "B":
                    if (curIdx > 1) {
                        curIdx -= 1;
                        inputs.remove(curIdx);
                    }
                    break;
                case "P":
                    if(curIdx < inputs.size()) {
                        curIdx += 1;
                    }
                    inputs.add(curIdx, command[1]);
                    break;
            }
        }
        StringBuilder result = new StringBuilder();
        for (String input:inputs) {
            result.append(input);
        }
        System.out.println(result);
 */