import java.util.Scanner;

public class Main {
        static long[] pre;
        static long[] temp;
        static int lower, upper;

        public static void main(String[] args) {

                Scanner sc = new Scanner(System.in);

                lower = sc.nextInt();

                upper = sc.nextInt();

                int n = sc.nextInt();

                int[] a = new int[n];

                for (int i = 0; i < n; i++) {

                        a[i] = sc.nextInt();

                }
                System.out.println(countRangeSum(a, lower, upper));

        }

        static long countRangeSum(int[] nums, int lower, int upper) {
                int n = nums.length;
                pre = new long[n + 1];
                temp = new long[n + 1];

                for (int i = 0; i < n; i++) {
                        pre[i + 1] = nums[i] + pre[i];
                }
                return mergeCount(0, n + 1);
        }

        static long mergeCount(int left, int right) {
                if (right - left <= 1) {
                        return 0;
                }
                int mid = left + (right - left) / 2;

                long count = mergeCount(left, mid) + mergeCount(mid, right);

                int l = left;
                int r = left;

                for (int j = mid; j < right; j++) {

                        while (l < mid && pre[l] < pre[j] - upper) {
                                l++;
                        }
                        while (r < mid && pre[r] <= pre[j] - lower) {
                                r++;
                        }
                        count += r - l;
                }

                int i = left, j = mid, k = left;
                while (i < mid && j < right) {
                        if (pre[i] <= pre[j]) {
                                temp[k++] = pre[i++];
                        } else {
                                temp[k++] = pre[j++];
                        }
                }
                while (i < mid) {
                        temp[k++] = pre[i++];
                }
                while (j < right) {
                        temp[k++] = pre[j++];
                }
                for (int p = left; p < right; p++) {
                        pre[p] = temp[p];
                }
                return count;
        }
}