import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<int[]> rows = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            int[] row = new int[parts.length];

            for (int j = 0; j < parts.length; j++) {
                row[j] = Integer.parseInt(parts[j]);
            }

            rows.add(row);
        }

        // 将每一行组装成二维数组
        int m = rows.size();
        int[][] prices = new int[m][];

        for (int i = 0; i < m; i++) {
            prices[i] = rows.get(i);
        }
        System.out.println(maxCost(prices));

    }

    private static long maxCost(int[][] prices) {
        int m = prices.length;
        int n = prices[0].length;
        int[] all = new int[m * n];
        int index = 0;
        for (int[] shop : prices) {
            for (int price : shop) {
                all[index++] = price;
            }
        }
        Arrays.sort(all);
        long ans = 0;
        for (int i = 0; i < all.length; i++) {
            ans += (i + 1L) * all[i];
        }
        return ans;
    }
}
