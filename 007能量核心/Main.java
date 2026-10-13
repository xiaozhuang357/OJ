import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static char[][] grid;
    static int n, m;
    static int[][] dirs = {
            { 1, 0 },
            { -1, 0 },
            { 0, 1 },
            { 0, -1 }
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        m = sc.nextInt();

        grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = sc.next().toCharArray();
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '0') {
                    ans++;

                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();

        // 起点入列
        queue.offer(new int[] { x, y });

        // 标记已经访问
        grid[x][y] = '0';

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int i = cur[0];
            int j = cur[1];

            for (int[] dir : dirs) {
                int ni = i + dir[0];
                int nj = j + dir[1];

                // 判断是否出界
                if (ni < 0 || nj < 0 || ni >= n || nj >= m) {
                    continue;
                }

                // 如果是“0”说明已经走过
                if (grid[ni][nj] == '0') {
                    continue;
                }

                // 标记访问
                grid[ni][nj] = '0';

                // 加入队列
                queue.offer(new int[] { ni, nj });

            }
        }

    }
}