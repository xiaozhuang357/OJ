import java.util.Scanner;

public class Main {
    static long[] nums;

    static long[] temp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine().trim();
        String ss = s.substring(1, s.length() - 1);

        String[] c = ss.split(",");

        int n = c.length;
        nums = new long[n];
        temp = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(c[i]);
        }

        System.out.println(mergeSortCount(0, n - 1));

    }

    static long mergeSortCount(int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;

        long count = mergeSortCount(left, mid) + mergeSortCount(mid + 1, right);

        // 此时，A = [left,mid] 和 B = [mid+1,right]都是有序从小到大的了
        // 此时，按顺序对A中的每一个数，在B中查找符合条件的，即A[i] > B[j] * 2
        // 由于A，B均有序，在A[i] 处满足这个A[i] > B[j] * 2的，对后续，的[i,mid]都满足这个条件,
        // 这样，i和j都一致朝着一个方向走就可以，针对每一个A中的输，都有j - （mid + 1）个满足条件

        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && nums[i] > nums[j] * 2L) {
                j++;
            }
            count += j - (mid + 1);
        }
        // 合并两个有序数组，这一步是为了上一个获得的均为有序的数组找count的
        int i = left;
        j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        for (i = left; i <= right; i++) {
            nums[i] = temp[i];
        }
        return count;
    }
}
