package hard.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. Copy List with Random Pointer
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 *
 * Two Solutions:
 * Solution 1:
 * Use Hash Map to store (original node, copied node) pairs. Every time we copy a node, we need to see if this node exists in Hash Map.
 * If not, create a new node. If yes, connect to this node.
 *
 * Solution 2:
 * Don't use Hash Map.
 *             ___________
 *             |         |
 * Input: 1 -> 2 -> 3 -> 4
 *        |         |
 *        -----------
 *
 * First copy clone nodes
 * 1. 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> 4 -> 4'
 *
 * Now odd number represents original node, even number represents copied node.
 *
 * Then go through every odd number node. This node's random pointer's next will be the clone node's random pointer's desitination.
 *
 */
public class CopyListWithRandomPointer {
    static class RandomListNode {
             int label;
             RandomListNode next, random;
             RandomListNode(int x) { this.label = x; }
    }


    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        //Key = original node; Value = copied node
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode curNode = dummy;
        while (head != null) {
            //deep copy head
            RandomListNode next;
            if (map.containsKey(head)) {
                next = map.get(head);
            } else {
                next = new RandomListNode(head.label);
                map.put(head, next);
            }

            curNode.next = next;

            if (head.next != null) {
                if (map.containsKey(head.next)) {
                    next.next = map.get(head.next);
                } else {
                    next.next = new RandomListNode(head.next.label);
                    map.put(head.next, next.next);
                }

            }
            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    next.random = map.get(head.random);
                } else {
                    next.random = new RandomListNode(head.random.label);
                    map.put(head.random, next.random);
                }
            }
            curNode = next;
            head = head.next;
        }

        return dummy.next;
    }

    public RandomListNode copyRandomListNoHashMap(RandomListNode head) {
        if (head == null) {
            return head;
        }
        RandomListNode curNode = head;
        while (curNode != null) {
            RandomListNode clone = new RandomListNode(curNode.label);
            clone.next = curNode.next;
            curNode.next = clone;
            curNode = clone.next;
        }

        //go through every odd number node
        curNode = head;
        while (curNode != null) {
            if (curNode.random != null) {
                curNode.next.random = curNode.random.next;
            }
            curNode = curNode.next.next;
        }

        //go through every even number node
        head = head.next;
        curNode = head;
        while (curNode.next != null) {
            curNode.next = curNode.next.next;
            curNode = curNode.next;
        }

        return head;
    }

    private static void printLinkedList(RandomListNode head) {
        while (head != null) {
            System.out.print(head.label);
            if (head.next != null) {
                System.out.print("->");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer c = new CopyListWithRandomPointer();
        RandomListNode first = new RandomListNode(1);
        RandomListNode second = new RandomListNode(2);
        RandomListNode third = new RandomListNode(3);
        RandomListNode fourth = new RandomListNode(4);

        first.next = second;
        second.next = third;
        third.next = fourth;

        first.random = third;
        third.random = first;

        RandomListNode result = c.copyRandomListNoHashMap(first);
        printLinkedList(result);
        System.out.println(result.next.next.random.label);
    }

}
