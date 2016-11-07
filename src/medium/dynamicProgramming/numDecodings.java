package medium.dynamicProgramming;

/**
 * 91. Decode Ways
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 * Example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 *
 * Solution:
 * Dynamic Programming
 * M[i] stands for the number of expressions
 * M[i] = M[i - 1] (express i as a letter at self when 0 < i < 10)
 *      + M[i - 2] (express i with i - 1 when 10 <= substring(i-1, i) <= 26)
 *
 */
public class numDecodings {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];

        return memo[0];
    }

    public static void main(String[] args){
        numDecodings n = new numDecodings();
        System.out.println(n.numDecodings("1130"));
    }
}
