import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().trim().split("\\s+");

        int n = parts.length - 1;

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        System.out.println(effectiveSub(nums));

    }

    static final int MOD = 1_000_000_000 + 7;

    static int effectiveSub(int[] nums) {
        // 将0视为-1，求前缀和。只要一段区间的和>0就是有效子数组
        int n = nums.length;
        int[] pre = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + (nums[i] == 1 ? 1 : -1);
        }

        // 离散化，通过index来查找对应的前缀和
        int[] val = pre.clone();

        // 排序
        Arrays.sort(val);
        // 去重
        int m = 0; // 顺便记录有多少个前缀大小
        for (int i = 0; i < val.length; i++) {
            if (i == 0 || val[i] != val[i - 1]) {
                val[m++] = val[i];
            }
        }

        int[] bit = new int[m + 1]; // bit数组

        long ans = 0;

        // 先把pre[0]加入bit 因为后面是从1开始查找的
        add(bit, binarySearch(val, m, pre[0]), 1);

        for (int i = 1; i < pre.length; i++) {
            // 找到离散化的坐标
            int index = binarySearch(val, m, pre[i]);
            ans = (ans + query(bit, index - 1)) % MOD; // 这里查找的是严格小于的
            // 将当前前缀和加入到bit，后续查询使用
            add(bit, index, 1);
        }
        return (int) ans;
    }

    /*
     * 向树状数组binaryIndexedTree添加
     * bit:树状数组
     * pos要添加的位置
     * delta：变化量
     * 
     * 树状数组：
     * 根据下标的二进制，每一位都是到最后一位“1”的所有的和
     * 如：
     * bit存的是a[i]
     * bit[i] 含义：从 [i - lowerbit(i) + 1, i]之和
     * 7 -> 0111 最后一位1 0001，只有a[7]
     * 6 -> 0110 最后一位1 0010，包含前两个 即 a[5] + a[6]
     * 4 -> 0100 最后一位1 0100，包含前四个 即 a[1] + a[2] + a[3] + a[4]
     */
    static void add(int[] bit, int pos, int delta) {
        while (pos < bit.length) {
            bit[pos] += delta;
            pos += pos & (-pos); // 计算出pos的最后一位1
            // pos 0110 0110
            // -pos 1001 + 1 -> 1010
            // pos & (-pos) 0010
        }
    }

    static int query(int[] bit, int pos) {
        int prefixSum = 0;
        while (pos > 0) {
            prefixSum += bit[pos];
            pos -= pos & (-pos);
        }
        return prefixSum;
    }

    static int binarySearch(int[] arr, int bounder, int target) {
        int left = 0;
        int right = bounder - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left + 1; // 注意，这个应该返回1--based的index，因为如果返回0，会导致，0 & (-0) 死循环
    }
}
