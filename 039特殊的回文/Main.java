import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.print(longestDecomposition(string));
    }

    static int longestDecomposition(String s) {
        int left = 0;
        int right = s.length() - 1;
        int count = 0;

        while (left <= right) {
            if (left == right) {
                count++;
                break;
            }

            int maxLen = (right - left + 1) / 2;
            int matchLen = -1;

            for (int len = 1; len <= maxLen; len++) {
                if (match(s, left, right - len + 1, len)) {
                    matchLen = len;
                    break;
                }
            }

            if (matchLen == -1) {
                count++;
                break;
            }

            count += 2;
            left += matchLen;
            right -= matchLen;
        }
        return count;
    }

    static boolean match(String s, int left, int right, int len) {
        for (int i = 0; i < len; i++) {
            if (s.charAt(left + i) != s.charAt(right + i)) {
                return false;
            }
        }
        return true;
    }
}
