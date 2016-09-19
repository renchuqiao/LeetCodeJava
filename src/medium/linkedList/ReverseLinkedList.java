package medium.linkedList;

/**
 * 206. Reverse Linked List
 *
 * Reverse a singly linked list.
 *
 * Both iteratively and recursively.
 */
public class ReverseLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //The following is the recursive solution by LaiOffer.com
//    public ListNode reverseList(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode newHead = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
//    }

    //The following is the recursive solution by Chuqiao
    public ListNode reverseList(ListNode head) {
        return reverseListHelper(null, head);
    }

    private ListNode reverseListHelper(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode next = cur.next;
        cur.next = pre;
        return reverseListHelper(cur, next);
    }

    //The follwing is the iterative solution
    public ListNode reverseListIter(ListNode head) {
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode fourth = new ListNode(4);
        ListNode third = new ListNode(3);
        third.next = fourth;
        ListNode second = new ListNode(2);
        second.next = third;
        ListNode first = new ListNode(1);
        first.next = second;

        System.out.println(reverseLinkedList.reverseList(first).val);
        System.out.println(reverseLinkedList.reverseListIter(first).val);
    }

}
