package medium.string;

import medium.dynamicProgramming.PerfectSqures;

/**
 * 151. Reverse Words in a String
 * Given an input string, reverse the string word by word.
 *
 * Example:
 * Given s = "the sky is blue",
 * return "blue is sky the".
 *
 *
 * Trick:
 * 1. Reverse the whole string
 * 2. Reverse each word in the string
 *
 * For instance
 * Input: "the sky is blue"
 * 1. "eulb si yks eht"
 * 2. "blue is sky the"
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {

        char[] chars = s.trim().toCharArray();

        if (chars.length == 0) {
            return "";
        }

        //truncate spaces in between
        //since we have already truncate leading spaces, chars[0] is always letter
        int slow = 1;
        int fast = 1;
        while (fast < chars.length) {
            if (chars[fast] == ' ' && chars[fast - 1] == ' ') {
                fast++;
            } else {
                chars[slow++] = chars[fast++];
            }
        }

        //reverse the whole string
        chars = reverseString(chars, 0, slow - 1);

        //reverse each word
        int i = 0;
        while (i < slow) {
            int j = i;
            while (j < slow) {
                if (chars[j] == ' ') {
                    break;
                }
                j++;
            }
            reverseString(chars, i, j - 1);
            //find next non-space
            i = j + 1;
        }

        return new String(chars, 0, slow);
    }

    private char[] reverseString(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
        return chars;
    }

    public static void main(String[] args) {
        ReverseWordsInAString reverseWordsInAString = new ReverseWordsInAString();
        System.out.println(reverseWordsInAString.reverseWords("the       sky is blue"));
    }
}
