package medium.linkedList;

/**
 * 92. Reverse Linked List II
 *
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 *
 * Example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 *
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 */
public class ReverseLinkedListII {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode cur = head;
        ListNode start = null;
        ListNode pre = null;
        ListNode next;
        int count = 1;
        while (cur != null && count <= m) {
            start = pre;
            pre = cur;
            cur = cur.next;
            count++;
        }

        while (cur != null && count <= n) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count++;
        }

        if (m == 1) {
            head.next = cur;
            head = pre;
        }

        if (start != null) {
            start.next.next = cur;
            start.next = pre;
        }

        return head;
    }

    private static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print("->");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ReverseLinkedListII reverseLinkedListII = new ReverseLinkedListII();
        ListNode fifth = new ListNode(5);
        ListNode fourth = new ListNode(4);
        fourth.next = fifth;
        ListNode third = new ListNode(3);
        third.next = fourth;
        ListNode second = new ListNode(2);
        second.next = third;
        ListNode first = new ListNode(1);
        first.next = second;
        printLinkedList(reverseLinkedListII.reverseBetween(first, 1, 4));
    }
}
