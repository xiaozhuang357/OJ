import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        List<String> result = find180RotationNum(a);
        String s = String.join(" ", result);
        System.out.println(s);
    }

    static char rotateChar(char c) {
        switch (c) {
            case '0':
                return '0';
            case '1':
                return '1';
            case '6':
                return '9';
            case '8':
                return '8';
            case '9':
                return '6';
            default:
                return c;
        }
    }

    public static List<String> find180RotationNum(int a) {
        // to do
        List<String> ans = new ArrayList<>();

        int half = a / 2;

        int[] first = { 1, 6, 8, 9 };
        int[] other = { 0, 1, 6, 8, 9 };
        int[] mid = { 0, 1, 8 };

        generate(ans, new StringBuilder(), 0, half, a, first, other, mid);
        return ans;
    }

    static void generate(List<String> ans, StringBuilder sb, int pos, int half, int a, int[] first, int[] other,
            int[] mid) {

        if (pos == half) {
            String left = sb.toString();
            String right = rotateString(left);

            if (a % 2 == 0) {
                ans.add(left + right);
            } else {
                for (int i : mid) {
                    ans.add(left + i + right);
                }
            }
            return;
        }
        int[] choices = (pos == 0) ? first : other;
        for (int i : choices) {
            sb.append(i);
            generate(ans, sb, pos + 1, half, a, first, other, mid);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    static String rotateString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(rotateChar(s.charAt(i)));
        }
        return sb.toString();
    }

}