package hard.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * Solution:
 * Use doubly linked list
 * leftmost = most recently used node
 * rightmost = least recently used node
 */
public class LRUCache {
    static class Node {
        int value;
        int key;
        Node next;
        Node prev;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private Node head;
    private Node tail;
    private final Map<Integer, Node> map;

    public LRUCache(final int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        Node node;
        if (map.containsKey(key)) {
            node = map.get(key);
            remove(node);
            append(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        Node node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            remove(node);
        } else if (map.size() < this.capacity) {
            node = new Node(key, value);
        } else {
            node = this.tail;
            //check if capacity == 0
            if(node == null) {
                return;
            }
            remove(this.tail);
            node.value = value;
            node.key = key;

        }
        append(node);
    }

    private void remove(Node node) {
        map.remove(node.key);
        if (head == tail) {
            head = tail = null;
            return;
        }

        if (node == this.head) {
            head = head.next;
            head.prev = null;
        } else if (node == this.tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.prev = node.next = null;
    }

    private void append(Node node) {
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
        node.prev = null;
    }

}
