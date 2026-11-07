import java.util.Scanner;

public class Main {
    static int m, n;
    static int[][] workers;
    static int[][] bikes;
    static int ans;
    static boolean used[];

    static int curDist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String array_info = sc.nextLine().trim(); // 工人坐标
        String array_info1 = sc.nextLine().trim(); // 自行车坐标

        workers = parsePoints(array_info);
        bikes = parsePoints(array_info1);

        m = workers.length;
        n = bikes.length;
        ans = Integer.MAX_VALUE;
        used = new boolean[n];

        backtrack(0);

        System.out.println(ans);

        sc.close();
    }

    // 解析 "x1,y1,x2,y2,..." 形式的字符串为点列表
    private static int[][] parsePoints(String s) {
        String[] parts = s.split(",");
        int cnt = parts.length / 2;
        int[][] points = new int[cnt][2];
        for (int i = 0; i < cnt; i++) {
            points[i][0] = Integer.parseInt(parts[2 * i].trim());
            points[i][1] = Integer.parseInt(parts[2 * i + 1].trim());
        }
        return points;
    }

    static void backtrack(int index) {
        if (index == m) {
            ans = Math.min(ans, curDist);
            return;
        }
        // 剪枝
        if (curDist >= ans) {
            return;
        }
        int[] w = workers[index];

        // 枚举自行车
        for (int i = 0; i < n; i++) {
            // 被占了，下一辆
            if (used[i]) {
                continue;
            }
            int d = Math.abs(w[0] - bikes[i][0]) + Math.abs(w[1] - bikes[i][1]);

            used[i] = true;
            curDist += d;

            backtrack(index + 1);

            used[i] = false;
            curDist -= d;
        }
    }
}