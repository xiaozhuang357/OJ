import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int dp1 = 1;
        int dp2 = 2;
        int cur = 0;

        if (n == 1) {
            System.out.println(1);
            return;
        }
        if (n == 2) {
            System.out.println(2);
            return;
        }

        int i = 3;
        while (i <= n) {
            cur = dp1 + dp2;
            dp1 = dp2;
            dp2 = cur;
            i++;
        }
        System.out.println(dp2);
    }

    // dp[i] = dp[i-1]+ dp[i-2];
}