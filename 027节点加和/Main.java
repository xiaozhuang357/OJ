import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s1 = sc.nextLine().split(" ");
        int[] arr1 = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            arr1[i] = Integer.parseInt(s1[i]);
        }
        String[] s2 = sc.nextLine().split(" ");
        int[] arr2 = new int[s2.length];
        for (int i = 0; i < s2.length; i++) {
            arr2[i] = Integer.parseInt(s2[i]);
        }
        ListNode l1 = arrayToLinkedList(arr1);
        ListNode l2 = arrayToLinkedList(arr2);
        ListNode result = nodeSummation(l1, l2);
        int[] arr = linkedListToArray(result);
        String[] stringArray = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            stringArray[i] = String.valueOf(arr[i]);
        }
        String res = String.join(" ", stringArray);
        System.out.println(res);
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int[] linkedListToArray(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        int[] array = new int[length];
        current = head;
        for (int i = 0; i < length; i++) {
            array[i] = current.val;
            current = current.next;
        }
        return array;
    }

    public static ListNode arrayToLinkedList(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int value : array) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummyHead.next;
    }

    public static ListNode nodeSummation(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }
        return dummy.next;
    }

}