package medium.linkedList;

/**
 * 2. Add Two Numbers
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * function: addTwoNumbers()
 *
 * Extension:
 * If the given two linked lists representing two non-negative numbers in order (not in reverse order), then we need to use
 * recursion to solve this problem.
 * addTwoNumbersReverse is the function for this problem.
 * addTwoNumbersReverseHelper is the helper function for recursive calls.
 *
 * Examples
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 8 -> 0 -> 7
 * (head is the most significant number)
 *
 * Solution:
 * first fill out the leading 0's of the shorter linked list
 * then use recursion to find the tail of two linked list and then add two nodes
 *
 * Note: Don't forget to add one extra 1 at the head if carry is 1
 *
 * For instance:
 * Input: (9 -> 9) + (9 -> 9 -> 9)
 * 1. (0 -> 9 -> 9) + (9 -> 9 -> 9)
 * 2. (0 -> 9 -> 8) with carry = 1
 * 3. (1 -> 0 -> 9 -> 8) output
 */
public class AddTwoNumbers {
    static class ListNode {
        final int val;
        ListNode next;
        ListNode(final int val) {
            this.val = val;
        }
    }

    static class NewListNode {
        final int carry;
        final ListNode node;
        NewListNode(final ListNode node, final int carry) {
            this.node = node;
            this.carry = carry;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        int sum = 0;
        ListNode dummy = new ListNode(0);
        ListNode curNode = dummy;
        while (l1 != null && l2 != null) {
            sum /= 10;
            sum = l1.val + l2.val + sum;
            curNode.next = new ListNode(sum % 10);
            curNode = curNode.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode remain = (l1 != null) ? l1 : l2;

        while (remain != null) {
            sum /= 10;
            if (sum == 1) {
                sum += remain.val;
                curNode.next = new ListNode(sum % 10);
                curNode = curNode.next;
                remain = remain.next;
            } else {
                curNode.next = remain;
                break;
            }
        }

        if (sum/10 == 1) {
            curNode.next = new ListNode(1);
        }

        return dummy.next;
    }

    public ListNode addTwoNumbersReverse(ListNode l1, ListNode l2) {
        //find length of l1
        int len1 = 0;
        ListNode curNode = l1;
        while (curNode != null) {
            len1++;
            curNode = curNode.next;
        }

        //find length of l2
        int len2 = 0;
        curNode = l2;
        while (curNode != null) {
            len2++;
            curNode = curNode.next;
        }

        curNode = (len1 > len2) ? l2 : l1;

        int count = 0;
        //fill in 0
        while (count < Math.abs(len1 - len2)) {
            ListNode newNode = new ListNode(0);
            newNode.next = curNode;
            curNode = newNode;
            count++;
        }

        if (len1 > len2) {l2 = curNode;}
        else {l1 = curNode;}

        NewListNode newNode = addTwoNumbersReverseHelper(l1, l2);

        //add extra 1 at the head
        if (newNode.carry == 1) {
            curNode = new ListNode(1);
            curNode.next = newNode.node;
        } else {
            curNode = newNode.node;
        }

        return curNode;
    }

    private NewListNode addTwoNumbersReverseHelper(ListNode l1, ListNode l2) {
        //base case
        if (l1.next == null) {
            return new NewListNode(new ListNode((l1.val + l2.val) % 10), (l1.val + l2.val) / 10);
        }
        NewListNode nextNode = addTwoNumbersReverseHelper(l1.next, l2.next);
        int sum = nextNode.carry + l1.val + l2.val;
        ListNode curNode = new ListNode(sum % 10);
        curNode.next = nextNode.node;
        return new NewListNode(curNode, sum/10);
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
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        ListNode fourth = new ListNode(9);
        ListNode third = new ListNode(9);
        third.next = fourth;
        ListNode second = new ListNode(9);
        second.next = third;

        ListNode fourth2 = new ListNode(9);
        ListNode third2 = new ListNode(9);
        third2.next = fourth2;

        printLinkedList(addTwoNumbers.addTwoNumbersReverse(second, third2));
    }

}
