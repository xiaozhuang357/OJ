import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int num;
    static int best;
    static int[] cnt = new int[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();

        int starLen = String.valueOf(num).length();

        // 位数从小到大，找到某位数就输出
        for (int len = starLen; len <= 9; len++) {
            best = Integer.MAX_VALUE;

            // 用mask的二进制的每一位表示当前位是否选上了
            for (int mask = 1; mask < (1 << 9); mask++) {
                int sum = 0;
                Arrays.fill(cnt, 0);

                for (int j = 1; j <= 9; j++) {
                    if ((mask & (1 << (j - 1))) != 0) {
                        cnt[j] = j;
                        sum += j;
                    }
                }
                if (len != sum) {
                    continue;
                }

                dfs(0, len, 0);
            }

            if (best != Integer.MAX_VALUE) {
                System.out.println(best);
                return;
            }
        }
    }

    static void dfs(int pos, int len, int cur) {
        if (pos == len) {
            if (cur > num && cur < best) {
                best = cur;
            }
            return;
        }

        // 剪枝，如果当前cur后面全部填进去1也比best大，直接剪掉
        int minPossible = cur;
        for (int i = pos; i < len; i++) {
            minPossible = minPossible * 10 + 1;
        }
        if (minPossible >= best) {
            return;
        }

        // 从小到大，找到最小的
        for (int i = 1; i <= 9; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                dfs(pos + 1, len, cur * 10 + i);
                cnt[i]++;
            }
        }
    }
}
