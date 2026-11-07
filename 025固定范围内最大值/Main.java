import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int windSize = sc.nextInt();
        if (windSize > n || windSize == 0) {
            System.out.println();
        } else {
            PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
            int left = 0;
            int right = 0;
            while (right < n) {
                queue.offer(nums[right]);
                right++;
                if (right < windSize) {
                    continue;
                }
                System.out.print(queue.peek() + " ");
                queue.remove(nums[left]);
                left++;
            }
        }
    }
}