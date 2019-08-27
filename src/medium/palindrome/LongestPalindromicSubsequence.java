package medium.palindrome;

/**
 * Leetcode 516. Longest Palindromic Subsequence
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
       int length = s.length();
       int[][] m = new int[length][length];
       int score;
       for (int i = 0; i < length; i++) {
           for (int j = length - 1; j >= 0; j--) {
               if (i == j) {
                   m[i][j] = 1;
               } else if (j > i) {
                   m[i][j] = 0;
               }
               else {
                   if (s.charAt(i) == s.charAt(j)) {
                       score = Math.max(m[i-1][j+1] + 2, m[i - 1][j]);
                       score = Math.max(score, m[i][j+1]);
                   } else {
                       score = Math.max(m[i][j+1], m[i - 1][j]);
                   }
                   m[i][j] = score;
               }
           }
       }
       return m[length - 1][0];
    }
}
