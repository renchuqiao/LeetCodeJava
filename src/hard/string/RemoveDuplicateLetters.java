package hard.string;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.jar.Pack200;

/**
 * 316. Remove Duplicate Letters
 * - We are looking for the longest string in monotonically ascending order. This string breaks when some letters only
 * has one occurrence (at the moment we are trying to add it).
 * 1. We need to count the occurrence of each letter
 * 2. We used a stack to track the monotonically ascending string. If current letter is alphabetically smaller than the
 * top of the stack, we will pop it. We pop until we find the letter in stack that is smaller or the same as the current letter.
 * We then add the current letter to the stack.
 * Note that we need to keep decreasing the count even though we didn't add the letter to the stack (or pop it)
 * 3. The stack contains the result reversely. We use StringBuilder to build the string reversely.
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
    	int[] count = new int[26];
	    boolean[] visited = new boolean[26];
        StringBuilder sb = new StringBuilder();

	    Deque<Character> stack = new LinkedList<>();

	    for (int i = 0; i < s.length(); i++) {
		    count[s.charAt(i) - 'a']++;
	    }

        for (int i = 0; i < s.length(); i++) {
            if (visited[s.charAt(i) - 'a']) {
                count[s.charAt(i) - 'a']--;
                continue;
            }
            while (!stack.isEmpty() && s.charAt(i) < stack.peekLast() && count[stack.peekLast() - 'a'] > 0) {
                visited[stack.pollLast() - 'a'] = false;
            }
                stack.offerLast(s.charAt(i));
                visited[stack.peekLast() - 'a'] = true;
                count[stack.peekLast() - 'a']--;
        }

        while (!stack.isEmpty()) {
            sb.insert(0, stack.pollLast());
        }

        return sb.toString();
			
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters r = new RemoveDuplicateLetters();
        System.out.println(r.removeDuplicateLetters("cbacdcbc"));
        System.out.println(r.removeDuplicateLetters("bcabc"));
    }
}
