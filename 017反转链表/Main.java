import java.util.Scanner;

public class Main {

    // 链表节点
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入示例：[3 6 4 1]
        String input = sc.hasNextLine() ? sc.nextLine() : "";

        // 1. 根据输入构造链表
        ListNode head = buildList(input);

        // 2. 反转链表，并将节点值存入数组
        int[] ans = reverseToArray(head);

        // 3. 按空格分隔输出，不带方括号和逗号
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < ans.length; i++) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(ans[i]);
        }

        // 空数组时输出空行
        System.out.println(output);
    }

    // 把输入字符串转换成链表
    public static ListNode buildList(String input) {
        // 去掉方括号以及首尾空白
        input = input.replace("[", "")
                .replace("]", "")
                .trim();

        // 空链表
        if (input.isEmpty()) {
            return null;
        }

        // 按一个或多个空白字符分割
        String[] values = input.split("\\s+");

        // 虚拟头节点，方便统一处理节点的插入
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (String value : values) {
            tail.next = new ListNode(Integer.parseInt(value));
            tail = tail.next;
        }

        // dummy 本身不是输入链表的一部分
        return dummy.next;
    }

    // 反转链表，并把反转后的节点值存入数组
    public static int[] reverseToArray(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        int n = 0;

        // 反转链表，同时统计节点数量
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;

            n++;
        }

        // prev 是反转后的头节点
        int[] ans = new int[n];
        cur = prev;

        for (int i = 0; i < n; i++) {
            ans[i] = cur.val;
            cur = cur.next;
        }

        return ans;
    }
}