import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] values = input.split(",");
        TreeNode root = createTreeNode(values);
        int[] res = action(root);
        System.out.print(Math.max(res[0], res[1]));
    }

    static TreeNode createTreeNode(String[] values) {

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int idx = 1;

        // 满二叉树
        while (idx < values.length && !queue.isEmpty()) {

            TreeNode cur = queue.poll();

            // 左孩子
            cur.left = new TreeNode(Integer.parseInt(values[idx++]));
            queue.offer(cur.left);

            // 右孩子
            cur.right = new TreeNode(Integer.parseInt(values[idx++]));
            queue.offer(cur.right);

        }

        return root;
    }

    // ans[0] 选择当前节点
    // ans[1] 不选当前节点
    static int[] action(TreeNode root) {
        if (root == null) {
            return new int[] { 0, 0 };
        }
        int[] left = action(root.left);
        int[] right = action(root.right);

        // 选择当前节点
        int robCur = left[1] + right[1] + root.val;
        // 不选当前节点 选择两边最大的
        int notRobCur = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] { robCur, notRobCur };

    }

    static class TreeNode {
        int val;

        TreeNode left, right;

        public TreeNode(int _val) {
            val = _val;
        }
    }

}
