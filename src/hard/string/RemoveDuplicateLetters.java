package hard.string;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * TODO
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
    	int[] count = new int[26];
	    boolean[] visited = new boolean[26];

	    Deque stack = new LinkedList();

	    for (int i = 0; i < s.length(); i++) {
		    count[s.charAt(i) - 'a']++;
	    }

        return "";
			
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters r = new RemoveDuplicateLetters();
        System.out.println(r.removeDuplicateLetters("cbacdcbc"));
        System.out.println(r.removeDuplicateLetters("bcabc"));
    }
}
