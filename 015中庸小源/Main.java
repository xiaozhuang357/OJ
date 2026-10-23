import java.util.Scanner;

public class Main {
    static int m, n;
    static int[][] map;
    static int[][] dirs = {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    };
    static boolean[][] vis;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();

        map = new int[m][n];
        vis = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        sc.close();

        // 枚举起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = true;
                dfs(i, j, 0, 0);
                vis[i][j] = false; // 恢复现场
            }
        }

        System.out.println(ans);
    }

    static void dfs(int x, int y, int lastType, int steps) {
        if (steps > ans) {
            ans = steps;
        }
        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];
            // 判断是否越界
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                continue;
            }
            // 判断是否访问过
            if (vis[nx][ny]) {
                continue;
            }
            // 判断当前走的这一步的类型
            int curH = map[x][y];
            int nexH = map[nx][ny];
            // 等高不能走
            if (curH == nexH) {
                continue;
            }
            int nowType = curH > nexH ? -1 : 1;
            // 不能连续相同
            if (lastType != 0 && lastType == nowType) {
                continue;
            }
            vis[nx][ny] = true;
            dfs(nx, ny, nowType, steps + 1);
            // 恢复现场
            vis[nx][ny] = false;
        }

    }
}
