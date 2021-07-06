import java.io.*;
import java.util.ArrayList;

import java.io.*;
        import java.lang.reflect.Array;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.regex.*;

class BalancedBracketsResult {

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isBalanced(String s) {
        int lenOfText = s.length();
        if(lenOfText%2==1 || lenOfText==0) return "NO";

        ArrayList<Character> waitForMatching = new ArrayList<Character>();
        for(int i=0; i<lenOfText; i++){

            if(waitForMatching.size()>0){
                Character lastVal = waitForMatching.get(waitForMatching.size()-1);
                if((s.charAt(i)=='}' && lastVal=='{') ||
                        (s.charAt(i)==']' && lastVal=='[') ||
                        (s.charAt(i)==')' && lastVal=='('))
                {
                    waitForMatching.remove(waitForMatching.size()-1);
                    continue;
                }
            }

            waitForMatching.add(s.charAt(i));
        }

        return waitForMatching.size()==0 ? "YES": "NO";
    }

}

public class BalancedBrackets {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String s = bufferedReader.readLine();

            String result = BalancedBracketsResult.isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
