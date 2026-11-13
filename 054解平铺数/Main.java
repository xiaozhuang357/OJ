import java.util.Scanner;

public class Main {

    static int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(tiling(n));
    }

    static int tiling(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int dp0 = 1;
        int dp1 = 1;
        int dp2 = 2;
        int cur = 0;

        for (int i = 3; i <= n; i++) {
            cur = (2 * dp2 % MOD + dp0) % MOD;
            dp0 = dp1;
            dp1 = dp2;
            dp2 = cur;
        }

        return dp2;
    }
}