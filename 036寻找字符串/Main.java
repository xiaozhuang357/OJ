import java.util.Scanner;

public class Main {
    static char[][] board;
    static boolean[][] visited;
    static int m, n;
    static String str;
    static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        str = sc.nextLine().trim();

        m = sc.nextInt();
        n = sc.nextInt();

        sc.nextLine();

        visited = new boolean[m][n];
        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            String[] parts = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                board[i][j] = parts[j].charAt(0);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) {
                    System.out.println("true");
                    return;
                }
            }
        }

        System.out.println("false");
    }

    static boolean dfs(int x, int y, int index) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return false;
        }
        if (visited[x][y] || board[x][y] != str.charAt(index)) {
            return false;
        }

        if (index == str.length() - 1) {
            return true;
        }

        visited[x][y] = true;
        for (int[] dir : DIRS) {
            if (dfs(x + dir[0], y + dir[1], index + 1)) {
                visited[x][y] = false;
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }
}
