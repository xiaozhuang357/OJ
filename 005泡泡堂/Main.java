import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        char[][] matrix = parseStringTo2DArray(input);
        System.out.println(maxKilledEnemies2(matrix));
    }

    public static char[][] parseStringTo2DArray(String input) {
        // 去除首尾多余的 {{ 和 }}
        input = input.trim().replaceAll("^\\{\\{|\\}\\}$", "");
        // 分割成行
        String[] rows = input.split("\\},\\{");
        List<char[]> matrixList = new ArrayList<>();
        for (String row : rows) {
            // 清理每行的多余符号并分割成字符
            String[] chars = row.trim().replaceAll("[{}']", "").split(",");
            char[] charRow = new char[chars.length];
            for (int i = 0; i < chars.length; i++) {
                charRow[i] = chars[i].trim().charAt(0);
            }
            matrixList.add(charRow);
        }
        // 将列表转换为二维数组
        int rowCount = matrixList.size();
        int colCount = matrixList.get(0).length; // 假设所有行有相同的列数
        char[][] matrix = new char[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            matrix[i] = matrixList.get(i);
        }
        return matrix;
    }

    // 蛮力法
    public static int maxKilledEnemies1(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] != 'S') {
                    continue;
                }

                int count = 0;

                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    while (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] != 'B') {
                        if (grid[x][y] == 'E') {
                            count++;
                        }

                        x += dx[k];
                        y += dy[k];
                    }
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    // 动态规划
    public static int maxKilledEnemies2(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // 四个方向的dp数组
        int[][] left = new int[rows][cols];
        int[][] right = new int[rows][cols];
        int[][] up = new int[rows][cols];
        int[][] down = new int[rows][cols];

        // 从左往右
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'B') {
                    left[i][j] = 0;
                } else {
                    if (j > 0) {
                        left[i][j] = left[i][j - 1];
                    }
                    if (grid[i][j] == 'E') {
                        left[i][j]++;
                    }
                }
            }
        }

        // 从上往下
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'B') {
                    up[i][j] = 0;
                } else {
                    if (i > 0) {
                        up[i][j] = up[i - 1][j];
                    }
                    if (grid[i][j] == 'E') {
                        up[i][j]++;
                    }
                }
            }
        }
        // 从右往左
        for (int i = 0; i < rows; i++) {
            for (int j = cols - 1; j >= 0; j--) {
                if (grid[i][j] == 'B') {
                    right[i][j] = 0;
                } else {
                    if (j < cols - 1) {
                        right[i][j] = right[i][j + 1];
                    }
                    if (grid[i][j] == 'E') {
                        right[i][j]++;
                    }
                }
            }
        }

        // 从下往上
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'B') {
                    down[i][j] = 0;
                } else {
                    if (i < rows - 1) {
                        down[i][j] = down[i + 1][j];
                    }
                    if (grid[i][j] == 'E') {
                        down[i][j]++;
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'S') {
                    int count = left[i][j] +
                            right[i][j] +
                            up[i][j] +
                            down[i][j];
                    ans = Math.max(ans, count);
                }
            }
        }

        return ans;
    }
}