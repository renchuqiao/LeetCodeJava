package hard.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 2781. Length of the Longest Valid Substring
 *
 * Naive Solution:
 * Two pointer to go through the word and two pointers to check the substring of the word.
 * This cannot pass the Leetcode time Limit
 *
 * More optimal solution:
 *
 *
 */
public class LengthOfTheLongestValidSubstring {

    public int longestValidSubstring(String word, List<String> forbidden) {
        int maxLength = 0;
        HashSet<String> f = new HashSet<>(forbidden);
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < word.length(); j++) {
                sb.append(word.charAt(j));
                if (isValidString(sb.toString(), f)){
                    int length = j - i + 1;
                    maxLength = Math.max(length, maxLength);
                }
            }
        }
        return maxLength;
    }

    private boolean isValidString(String word, HashSet<String> forbidden) {
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < word.length(); j++) {
                sb.append(word.charAt(j));
                if (forbidden.contains(sb.toString())){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LengthOfTheLongestValidSubstring l = new LengthOfTheLongestValidSubstring();
        String word = "ccaaacbcbbcaccacacccbbcaaabcabbbaccccbcabacccacabcbbbcabccabacccaababcccccbbbcccaabbbbcbbaa";
        List<String> forbidden = new ArrayList<>();
        forbidden.add("cbbabcaa");
        forbidden.add("bbcbaaca");
        forbidden.add("abcccccbbe");
        forbidden.add("bcaccaaac");
        forbidden.add("ccbcaba");
        forbidden.add("cccacabc");
        forbidden.add("bbbccca");
        forbidden.add("ccabaccca");
        forbidden.add("bbbcabccab");
        forbidden.add("ccabaccca");
        System.out.println(l.longestValidSubstring(word, forbidden));
    }
}
