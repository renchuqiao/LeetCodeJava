package easy;

/**
 * 28. Implement strStr()
 *
 * Implement strStr().
 *
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Solution:
 * Robin-Karp Algorithm
 * First calculate the hash value of needle
 * since it is composed of 26 letters, the hash value of needle can be calculated as
 * 26^(# needle[0]) + 26^(# needle[1]) + ....
 * where # of each letter follows the alphabetical order:
 * a = 0
 * b = 1
 * .
 * .
 * .
 * z = 25
 *
 * Then, calculate the hash value of each substring in haystack
 *
 * For instance,
 * input:
 * haystack: a n e m b c j e
 * needle: m b c
 * first calculate the hash value of a n e
 * then hash value - #a + #m
 * then go on until the hash value of substring matches to the hash value of needle
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) {
            return -1;
        }

        if (needle.length() == 0) {
            return 0;
        }

        //calculate the hash value of needle
        long q = 101; //large prime
        long p = 23;  //small prime
        long hashNeedle = needle.charAt(0) % q;
        long rm = 1;  //used when remove a character below
        for (int i = 1; i < needle.length(); i++) {
            hashNeedle = (hashNeedle * p  + (long) needle.charAt(i)) % q;
            rm = (rm * p) % q;
        }


        long hashHaystack = 0;
        for (int i = 0; i < needle.length(); i++) {
            hashHaystack = (hashHaystack * p + (long) haystack.charAt(i)) % q;
        }


        if (hashHaystack == hashNeedle && check(haystack, needle, 0)) {
            return 0;
        }

        for (int i = needle.length(); i < haystack.length(); i++) {
            //Note: when we subtract the first character, we need to add q to avoid negative number
            hashHaystack = (hashHaystack + q - rm * (long) haystack.charAt(i - needle.length()) % q) % q;
            hashHaystack = ((hashHaystack * p + (long) haystack.charAt(i)) % q);

            if (hashHaystack == hashNeedle && check(haystack, needle, i - needle.length() + 1)) {
                return i - needle.length() + 1;
            }
        }

        return -1;
    }

    private boolean check(String haystack, String needle, int start) {
        for (int i = 0; i < needle.length(); i++) {
            if (haystack.charAt(i + start) != needle.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StrStr s = new StrStr();
        int i = s.strStr("", "");
        System.out.println(i);
    }
}
