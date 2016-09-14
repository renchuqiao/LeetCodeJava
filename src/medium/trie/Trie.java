package medium.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. Implement Trie (Prefix Tree)
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Assumptions:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Trie {
    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isLeaf;
        public TrieNode() {
            map = new HashMap<>();
            isLeaf = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (curNode.map.containsKey(c)) {
                curNode = curNode.map.get(c);
            } else {
                //insert to HashMap
                TrieNode newTrieNode = new TrieNode();
                curNode.map.put(c, newTrieNode);
                curNode = newTrieNode;
            }
        }
        //At the end of each word, change isLeaf to true
        curNode.isLeaf = true;

    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (curNode.map.containsKey(c)) {
                curNode = curNode.map.get(c);
            } else {
                return false;
            }
        }
        if (curNode.isLeaf) {
            return true;
        } else {
            return false;
        }
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode curNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            if (curNode.map.containsKey(c)) {
                curNode = curNode.map.get(c);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
        System.out.println(trie.search("abc"));
        System.out.println(trie.search("ab"));
        trie.insert("ab");
        System.out.println(trie.search("ab"));
    }
}
