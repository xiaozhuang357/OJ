import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int MOD = 1_000_000_000 + 7;
    static int m, n;
    static int[][][] memo;
    static final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        int maxMove = sc.nextInt();
        int starRow = sc.nextInt();
        int starColoum = sc.nextInt();

        memo = new int[m][n][maxMove + 1];
        for (int[][] a : memo) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        System.out.println(dfs(starRow, starColoum, maxMove));

    }

    private static long dfs(int i, int j, int rem) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }

        if (rem <= 0) {
            return 0;
        }
        if (memo[i][j][rem] != -1) {
            return memo[i][j][rem];
        }

        long count = 0;
        for (int[] dir : DIRS) {
            count += dfs(i + dir[0], j + dir[1], rem - 1);
        }
        memo[i][j][rem] = (int) (count % MOD);

        return memo[i][j][rem];

    }
}
