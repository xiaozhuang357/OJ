import java.util.Scanner;

public class Main {
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        sc.close();
        dp = new int[n + 1];
        dp[0] = n;

        System.out.println(minDays(n));
    }

    static int minDays(int n) {
        if (n <= 1) {
            return n;
        }
        dp[n] = Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3));
        return dp[n];
    }
}
