import java.util.*;

public class Main {
    static double[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n >= 20000) {
            System.out.printf("%.5f%n", 1.0);
            return;
        }

        int m = (n + 24) / 25;
        dp = new double[5][m + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i % 5][j] = 0.25 * (get(i - 4, j)
                        + get(i - 3, j - 1)
                        + get(i - 2, j - 2)
                        + get(i - 1, j - 3));
            }
        }

        System.out.printf("%.5f%n", get(m, m));
    }

    static double get(int a, int b) {
        if (a <= 0 && b <= 0)
            return 0.5;
        if (a <= 0)
            return 1.0;
        if (b <= 0)
            return 0.0;

        return dp[a % 5][b];
    }
}