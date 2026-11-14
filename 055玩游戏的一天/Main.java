import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int m, n;
    static char[][] board;
    static int k;
    static String word;
    static int[][] DIRS = {
            { 1, 0 }, { 1, 1 }, { 1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, { -1, 1 }
    };
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();

        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.next().charAt(0);
            }
        }
        k = sc.nextInt();
        List<String> candidates = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            candidates.add(sc.next());
        }

        List<String> ans = new ArrayList<>();
        for (String w : candidates) {
            if (canForm(w)) {
                ans.add(w);
            }
        }

        Collections.sort(ans);

        for (String s : ans) {
            System.out.println(s);
        }
    }

    static boolean canForm(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        if (s.length() > m * n) {
            return false;
        }

        word = s;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (dfs(i, j, 1)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    static boolean dfs(int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        char target = word.charAt(index);

        for (int[] dir : DIRS) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj] && board[ni][nj] == target) {
                visited[ni][nj] = true;
                if (dfs(ni, nj, index + 1)) {
                    return true;
                }
                visited[ni][nj] = false;
            }
        }

        return false;
    }
}
