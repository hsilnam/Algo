import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String password;
        while (!(password = br.readLine()).equals("end")) {
            if (isAcceptable(password)) {
                bw.write("<" + password + "> is acceptable.\n");
            } else {
                bw.write("<" + password + "> is not acceptable.\n");
            }
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static boolean isAcceptable(String password) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        boolean hasVowel = false;
        int vowelCount = 0, consonantCount = 0;
        char prevChar = '\0';
        
        for (char ch : password.toCharArray()) {
            boolean isVowel = vowels.contains(ch);
            if (isVowel) {
                hasVowel = true;
                vowelCount++;
                consonantCount = 0;
            } else {
                consonantCount++;
                vowelCount = 0;
            }
            
            if (vowelCount >= 3 || consonantCount >= 3) {
                return false;
            }
            
            if (ch == prevChar && ch != 'e' && ch != 'o') {
                return false;
            }
            prevChar = ch;
        }
        
        return hasVowel;
    }
}