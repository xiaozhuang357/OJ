import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = sc.nextInt();
            int[] nums = new int[n + 2];
            nums[0] = 0;
            nums[n + 1] = n + 1;
            for (int i = 1; i <= n; i++) {
                nums[i] = sc.nextInt();
            }

            int ans = solver(nums, n);

            if (ans >= 5) {
                sb.append("5 or more\n");
            } else {
                sb.append(ans).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    static int solver(int[] nums, int n) {
        int b0 = countBreakPoints(nums, n);
        if ((b0 + 2) / 3 >= 5) {
            return 5;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashMap<Long, Integer> bestG = new HashMap<>();

        long initial = encode(nums, n);
        bestG.put(initial, 0);
        pq.offer(new Node(nums, initial, 0, b0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.g != bestG.get(cur.code)) {
                continue;
            }

            if (cur.b == 0) { // 没有断点，意味着有序了
                return cur.g;
            }

            int[] a = cur.nums;

            for (int l = 0; l < n - 1; l++) {
                for (int m = l + 1; m < n; m++) {
                    for (int r = m + 1; r <= n; r++) {
                        int nextG = cur.g + 1;

                        int nextB = cur.b
                                - bad(a[l], a[l + 1])
                                - bad(a[m], a[m + 1])
                                - bad(a[r], a[r + 1])
                                + bad(a[l], a[m + 1])
                                + bad(a[r], a[l + 1])
                                + bad(a[m], a[r + 1]);

                        if (nextG + (nextB + 2) / 3 >= 5) {
                            continue;
                        }

                        int[] next = a.clone();
                        int lenX = m - l;
                        int lenY = r - m;

                        System.arraycopy(a, m + 1, next, l + 1, lenY);
                        System.arraycopy(a, l + 1, next, l + 1 + lenY, lenX);

                        // 对next再次判断去重
                        long key = encode(next, n);
                        Integer oldG = bestG.get(key);
                        if (oldG != null && oldG <= nextG) {
                            continue;
                        }
                        bestG.put(key, nextG);
                        pq.offer(new Node(next, key, nextG, nextB));
                    }
                }
            }
        }
        return 5;
    }

    // 返回是否为断点
    static int bad(int x, int y) {
        return y == x + 1 ? 0 : 1;
    }

    // 获取断点数量
    static int countBreakPoints(int[] nums, int n) {
        int cnt = 0;
        for (int i = 0; i <= n; i++) {
            cnt += bad(nums[i], nums[i + 1]);
        }
        return cnt;
    }

    // 把每一种排列变成二进制的数表示
    // 由于1 <= n <= 15 每个数的二进制，只占4位，然后一共需要60位，小于lang 的63位，就可以这样
    // 5 2 4 1 3
    // 0101 0010 0100 0001 0011 来表示这个排列
    static long encode(int[] nums, int n) {
        long code = 0L;
        for (int i = 1; i <= n; i++) {
            code |= (long) nums[i] << (4 * (i - 1));
        }
        return code;
    }

    static class Node implements Comparable<Node> {
        final int[] nums; // 当前排列（含哨兵）
        final long code; // 排列编码，用于判重
        final int g;// 已执行的操作次数
        final int b;// 断点数
        final int f;// 总操作次数的下界 f = g + ceil(b / 3);

        Node(int[] nums, long code, int g, int b) {
            this.nums = nums;
            this.code = code;
            this.g = g;
            this.b = b;
            this.f = g + (b + 2) / 3; // 计算f
        }

        @Override
        public int compareTo(Node other) {
            if (f != other.f) {
                return Integer.compare(f, other.f);
            }
            return Integer.compare(g, other.g);
        }

    }
}
