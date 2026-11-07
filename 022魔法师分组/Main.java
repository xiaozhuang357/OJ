import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取输入
        String[] numsInput = sc.nextLine().split(" ");
        int k = Integer.parseInt(sc.nextLine());

        // 转换成整数数组
        int[] nums = new int[numsInput.length];
        for (int i = 0; i < numsInput.length; i++) {
            nums[i] = Integer.parseInt(numsInput[i]);
        }

        // 判断是否可以将 nums 分成 k 个和相等的子集
        boolean result = canPartitionKSubsets(nums, k);

        // 输出结果
        System.out.println(result ? "true" : "false");

        sc.close();
    }

    // 判断是否可以将数组 nums 分成 k 个和相等的子集
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;

        Arrays.sort(nums);

        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        if (nums[0] > target) {
            return false;
        }

        int[] subsets = new int[k];
        int index = 0;
        return backtrack(nums, subsets, index, target);
    }

    // 回溯算法
    public static boolean backtrack(int[] nums, int[] subsets, int index, int target) {
        if (index == nums.length) {
            return true;
        }
        int num = nums[index];

        for (int i = 0; i < subsets.length; i++) {
            if (subsets[i] + num > target) {
                continue;
            }

            if (i > 0 && subsets[i] == subsets[i - 1]) {
                continue;
            }

            subsets[i] += num;

            if (backtrack(nums, subsets, index + 1, target)) {
                return true;
            }
            // 恢复现场
            subsets[i] -= num;
            if (subsets[i] == 0 || subsets[i] + num == target) {
                break;
            }
        }
        return false;
    }

}