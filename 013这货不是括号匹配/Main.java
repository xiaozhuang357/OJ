import java.util.*;

public class Main {
    static int pos;
    static String s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.nextLine().trim();
        System.out.println(parse());
    }

    static int parse() {
        int sum = 0;
        int max = 0;

        while (pos < s.length()) {
            char c = s.charAt(pos);
            if (c == 'a') {
                sum++;
                pos++;
            } else if (c == '(') {
                pos++;
                sum += parse();
            } else if (c == '|') {
                max = Math.max(max, sum);
                sum = 0;
                pos++;
            } else if (c == ')') {
                pos++;
                break;
            }
        }
        return Math.max(max, sum);
    }
}
