import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Integer> path;
    static List<List<Integer>> ans;
    static int K;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int n = scanner.nextInt();

        List<List<Integer>> result = combinationSum3(k, n);

        for (List<Integer> combination : result) {
            System.out.println(String.join(" ", combination.stream().map(String::valueOf).toArray(String[]::new)));
        }

        scanner.close();
    }

    // 主函数：生成所有和为 n 的 k 个数组合
    public static List<List<Integer>> combinationSum3(int k, int n) {
        path = new ArrayList<>();
        ans = new ArrayList<>();
        K = k;
        dfs(1, n);
        return ans;
    }

    /**
     * 从 i 开始，从小到大枚举下一个可选的数字
     * 
     * @param i    当前可选的最小数字
     * @param rest 还差多少到目标和 N
     */
    static void dfs(int i, int rest) {
        int n = K - path.size();

        // 剪枝：如果当前最大的加和都不能达到rest，或者最小值都比rest大，直接返回
        int max = (9 + 9 - (n - 1)) * n / 2; // 全局上界
        int min = (i + i + n - 1) * n / 2;// 局部下界
        if (rest < min || max < rest) {
            return;
        }

        if (n == 0) {
            if (rest == 0) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }

        for (int j = i; j <= 9 - n + 1; j++) {
            path.add(j);
            dfs(j + 1, rest - j);
            path.remove(path.size() - 1); // 恢复现场
        }

    }

}