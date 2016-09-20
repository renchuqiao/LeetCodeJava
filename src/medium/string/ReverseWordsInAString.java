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
        char[] chars = s.toCharArray();
        //reverse the whole string
        chars = reverseString(chars, 0, chars.length - 1);

        //reverse each word
        int i = 0;
        while (i < chars.length) {
            int j = i;
            while (j < chars.length) {
                if (chars[j] == ' ') {
                    break;
                }
                j++;
            }
            reverseString(chars, i, j - 1);
            i = j+1;
        }

        return new String(chars);
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
        System.out.println(reverseWordsInAString.reverseWords("the sky is blue"));
    }
}
