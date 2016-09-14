package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example
 *
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 *
 * Assumption:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        if (s.length() < 2) {
            return (s.equals(t)) ? s : "";
        }

        Map<Character, Integer> map = new HashMap<>();

        //Put all t's characters into the map
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        int begin = 0;
        int end = 0;
        int finalBegin = 0;
        int finalEnd = 0;

        //find the first window
        while (end < s.length() && !containsAll(map)) {
            if (map.containsKey(s.charAt(end))) {
                map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
            }
            end++;
        }

        if (end >= s.length() && !containsAll(map)) {
            return "";
        }

        while (end <= s.length()) {
            //move begin pointer
            boolean inLoop = false;
            while (begin < end && containsAll(map)) {
                inLoop = true;
                if (map.containsKey(s.charAt(begin))) {
                    map.put(s.charAt(begin), map.get(s.charAt(begin)) + 1);
                }
                begin++;
            }

            if (inLoop) {
                begin--;
                if (map.containsKey(s.charAt(begin))) {
                    map.put(s.charAt(begin), map.get(s.charAt(begin)) - 1);
                }
            }

            //update final begin and final end
            if (containsAll(map)) {
                finalBegin = begin;
                finalEnd = end;
            }


            //move window pointer
            if (map.containsKey(s.charAt(begin))) {
                map.put(s.charAt(begin), map.get(s.charAt(begin)) + 1);
            }
            begin++;

            if (end < s.length()) {
                if (map.containsKey(s.charAt(end))) {
                    map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
                }
            }

            end++;
        }


        return s.substring(finalBegin, finalEnd);

    }

    private boolean containsAll(Map<Character, Integer> map) {
        for (Integer i : map.values()) {
            if (i > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println("Solution = " + minimumWindowSubstring.minWindow("ab", "b"));
        System.out.println("Solution = " + minimumWindowSubstring.minWindow("aa", "aa"));
        System.out.println("Solution = " + minimumWindowSubstring.minWindow("abc", "ac"));
        System.out.println("Solution = " + minimumWindowSubstring.minWindow("bdab", "ab"));
        System.out.println("Solution = " + minimumWindowSubstring.minWindow("cabefgecdaecf", "cae"));
        System.out.println("Solution = " + minimumWindowSubstring.minWindow("ab", "A"));


    }
}
