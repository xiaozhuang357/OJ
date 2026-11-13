import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String s;
    static int n;
    static List<BigInteger> path;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine().trim();
        n = s.length();
        path = new ArrayList<>();

        System.out.println(dfs(0) ? "true" : "false");

    }

    static boolean dfs(int i) {
        if (i == n) { // 到达末尾，至少需要包含三个数才算成功
            return path.size() >= 3;
        }

        for (int j = i + 1; j <= n; j++) {
            // 剪枝，多位数不能以“0”为开头
            if (j - i > 1 && s.charAt(i) == '0') {
                break;
            }

            // 取出下一个数
            BigInteger val = new BigInteger(s.substring(i, j));

            // 如果path中存在两个数以上，那么val要等于前两个数之和
            if (path.size() >= 2) {
                BigInteger sum = path.get(path.size() - 1).add(path.get(path.size() - 2));

                // 剪枝，如果当前val已经大于sum，再延长也无用
                if (val.compareTo(sum) > 0) {
                    break;
                }

                if (!val.equals(sum)) {
                    continue;
                }
            }

            path.add(val);
            if (dfs(j)) {
                return true;
            }
            path.remove(path.size() - 1);
        }

        return false;
    }
}
