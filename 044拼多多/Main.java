import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // 每张贴纸有的字符个数
    static int[][] stickersCnt;

    // 拼出当前字符串还需要多少贴纸
    static Map<String, Integer> memo;

    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        stickersCnt = new int[n][26];

        boolean[] isPre = new boolean[26];
        // 统计贴纸中的字符个数和每种字符是否存在
        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                stickersCnt[i][c - 'a']++;
                isPre[c - 'a'] = true;
            }
        }

        // 看是否可以拼出来这个target
        for (char c : target.toCharArray()) {
            if (!isPre[c - 'a']) {
                return -1;
            }
        }

        memo = new HashMap<>();

        // 初始化，空字符串不需要贴纸
        memo.put("", 0);
        char[] chars = target.toCharArray();

        // 对target进行排序
        Arrays.sort(chars);

        return dfs(new String(chars));

    }

    // 计算拼出当前字符串需要多少贴纸
    static int dfs(String rest) {

        // 直接返回记忆化结果
        if (memo.containsKey(rest)) {
            return memo.get(rest);
        }

        // 找到包含rest第一个字符的贴纸，然后计算完全使用一张这个贴纸后的rest

        int[] need = new int[26]; // 当前rest需要的每种字符个数

        for (char c : rest.toCharArray()) {
            need[c - 'a']++;
        }

        int best = Integer.MAX_VALUE / 2;
        // 找到包含rest第一个字符的贴纸
        for (int i = 0; i < stickersCnt.length; i++) {
            if (stickersCnt[i][rest.charAt(0) - 'a'] == 0) {
                continue;
            }

            // 计算完全使用这张贴纸后的下一个rest字符串next
            // restChars中就是有序的需要的字符
            StringBuilder next = new StringBuilder();
            for (int c = 0; c < 26; c++) {
                int remain = need[c] - stickersCnt[i][c];
                // 如果还有剩余，就添加进next字符串中，依旧是有序的
                for (int j = 0; j < remain; j++) {
                    next.append((char) (c + 'a'));
                }
            }

            // 计算出剩余的next字串还需要多少贴纸
            int nextCnt = dfs(next.toString());
            // 当前这个target需要的贴纸数 = next需要的贴纸数+1
            if (nextCnt != -1) {
                best = Math.min(best, nextCnt + 1);
            }
            // rest不为空，至少也需要一张贴纸
            // 当best == 1代表找到了最优解
            if (best == 1) {
                break;
            }
        }
        int ans = best == Integer.MAX_VALUE / 2 ? -1 : best;
        // 更新记忆
        memo.put(rest, ans);
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] stickers = scanner.nextLine().trim().split("\\s+");

        String target = scanner.nextLine().trim();

        Main solution = new Main();
        System.out.println(solution.minStickers(stickers, target));
    }
}