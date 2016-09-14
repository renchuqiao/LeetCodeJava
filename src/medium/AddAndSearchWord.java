package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 211. Add and Search Word - Data structure design
 *
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 *
 *
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *
 * Assumption:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 * Example:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 */
public class AddAndSearchWord {

    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isLeaf;
        public TrieNode() {
            map = new HashMap<>();
            isLeaf = false;
        }
    }

    private TrieNode root;

    public AddAndSearchWord() {
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(root, word, 0);

    }

    private boolean search(TrieNode curNode, String word, int index) {
        //base case
        if (index == word.length()) {
            return curNode.isLeaf;
        }
        Character c = word.charAt(index);
        if (c == '.') {
            boolean tempResult = false;
            for (TrieNode t : curNode.map.values()) {
                tempResult |= search(t, word, index + 1);
            }
            return tempResult;
        } else {
            if (curNode.map.containsKey(c)) {
                return search(curNode.map.get(c), word, index + 1);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        AddAndSearchWord addAndSearchWord = new AddAndSearchWord();
        addAndSearchWord.addWord("at");
        addAndSearchWord.addWord("bat");
        System.out.println(addAndSearchWord.search(".at"));
    }
}
