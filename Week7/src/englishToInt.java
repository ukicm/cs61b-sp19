import java.util.Scanner;

public class englishToInt {
    public static int letterNum(String s, int i) {
        int intChar = s.charAt(i);
        if ((intChar < 'a') || (intChar > 'z')) {
            throw new IllegalArgumentException();
        }
        return intChar - 'a' + 1;
    }

    public static int englishToInt(String s) {
        int intRep = 0;
        for (int i = 0; i < s.length(); i += 1) {
            intRep = intRep * 27;
            intRep = intRep + letterNum(s, i);
        }
        return intRep;
    }

    public static void main(String[] args) {
        String op = "bc";
        System.out.println(englishToInt(op));
    }
}
