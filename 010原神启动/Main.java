import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, m;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        int[] dp = new int[m + 1];
        int INF = 1_000_000_000;

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int h = sc.nextInt();

            int count = 1;

            while (a > 0) {

                int take = Math.min(a, count);
                int heal = take * h;

                for (int j = m; j >= 1; j--) {

                    int need = Math.max(0, j - heal);
                    if (dp[need] != INF) {
                        dp[j] = Math.min(dp[need] + heal, dp[j]);
                    }
                }
                a -= take;
                count *= 2;
            }
        }
        if (dp[m] == INF) {
            System.out.println("op");
        } else {
            System.out.println(dp[m] - m);
        }

    }
}
