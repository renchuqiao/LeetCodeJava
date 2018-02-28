package medium.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 267. Palindrome Permutation II
 * - Find all the letters that will be the half of the palindrome (first half)
 * - Separate the letter that only appeared once from others
 * - Terminate if more than one letter that only appeared once.
 * - If continue, for those numbers that appeared twice (or any even number times), generate permutation (Similar to Permutation II),
 * add the letter that appeared once, and then generate the rest (append the reverse string)
 */
public class PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        char[] arr = s.toCharArray();
        int[] map = new int[256]; //Assume ASCII letters only
        int odds = 0;
        StringBuilder sb = new StringBuilder();
        Character single = null;
        List<String> result = new ArrayList<>();

        if (s.length() == 0) {
            return result;
        }
        if (s.length() == 1) {
            result.add(s);
            return result;
        }

        for (char c : arr) {
            map[c]++;
            odds = (map[c] & 1) == 1 ? odds + 1 : odds - 1;
        }

        if (odds > 1) return result;

        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                if ((map[i] & 1) == 1) {
                    single = (char) i;
                }
                for (int j = 0; j < map[i]/2; j++) {
                    sb.append((char) i);
                }
            }
        }

        boolean[] used = new boolean[sb.length()];
        helper(sb.toString(), new StringBuilder(), used, single, result);
        return result;
    }

    private void helper(String s, StringBuilder temp, boolean[] used, Character single, List<String> result) {
        if (s.length() == temp.length()) {
            result.add(completePalindrome(new StringBuilder(temp), single));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!used[i] && set.add(s.charAt(i))) {
                used[i] = true;
                temp.append(s.charAt(i));
                helper(s, temp, used, single, result);
                used[i] = false;
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    private String completePalindrome(StringBuilder temp, Character single) {
        for (int i = temp.length() - 1; i > -1; i--) {
           temp.append(temp.charAt(i));
        }
        if (single != null) {
            temp.insert(temp.length()/2, single);
        }
        return new String(temp);
    }

    private void printResult(List<String> s) {
        for (String m : s) {
            System.out.println(m);
        }
    }

    public static void main(String[] args) {
        PalindromePermutationII p = new PalindromePermutationII();
        p.printResult(p.generatePalindromes("aaa"));
    }
}
