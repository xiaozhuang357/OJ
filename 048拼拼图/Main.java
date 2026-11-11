import java.util.Scanner;

public class Main {
    static int n;
    static int m;

    static boolean[][] uesd;

    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().trim().split(",");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        if (n == 0 || m == 0) {
            System.out.println(0);
            return;
        }

        if (n == m) {
            System.out.println(1);
            return;
        }
        if (n < m) {
            int t = m;
            m = n;
            n = t;
        }
        uesd = new boolean[n][m];
        // 贪心求上界
        ans = greedy(n, m);

        dfs(0, 0, 0);
        System.out.println(ans);
    }

    static int greedy(int i, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (i < j) {
            return greedy(i, j - i) + 1;
        }
        return greedy(i - j, j) + 1;
    }

    /**
     * 从 (x, y) 开始，前 x 行均已覆盖，第 x 行的前 y 列均已覆盖
     * 
     * @param x     左上角坐标
     * @param y     左上角坐标
     * @param count 当前正方形数量
     */
    static void dfs(int x, int y, int count) {
        if (count >= ans) {
            return;
        }

        // 全覆盖
        if (x == n) {
            ans = count;
            return;
        }

        // 这一行结束了，进入下一行
        if (y == m) {
            dfs(x + 1, 0, count);
            return;
        }

        // 当前格已经覆盖了，右移
        if (uesd[x][y]) {
            dfs(x, y + 1, count);
            return;
        }

        int maxLen = Math.min(n - x, m - y);
        for (int len = maxLen; len >= 1; len--) {
            if (!canPlace(x, y, len)) {
                continue;
            }

            fill(x, y, len, true);

            dfs(x, y, count + 1);

            fill(x, y, len, false);
        }

    }

    static boolean canPlace(int x, int y, int len) {
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (uesd[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static void fill(int x, int y, int len, boolean b) {
        for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                uesd[i][j] = b;
            }
        }
    }

}
