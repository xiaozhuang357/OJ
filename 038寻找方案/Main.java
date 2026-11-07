import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static int k;
    private static Map<Long, Integer> memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        memo = new HashMap<>();
        k = sc.nextInt();
        sc.close();
        System.out.println(dfs(1, 0, 0));
    }

    private static int dfs(long i, int jump, int down) {
        if (i > k + 1) {
            return 0;
        }

        long key = ((long) i << 6) | ((long) jump << 1) | down;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int count = (i == k) ? 1 : 0;

        count += dfs(i + (1L << jump), jump + 1, 0);

        if (down == 0 && i > 0) {
            count += dfs(i - 1, jump, 1);
        }
        memo.put(key, count);
        return count;
    }
}
