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

        //calculate the hash value of needle
        long hashNeedle = 0;
        for (int i = 0; i < needle.length(); i++) {
            hashNeedle = hashNeedle * 26  + (long) needle.charAt(i) - 'a';
        }


        long hashHaystack = 0;
        for (int i = 0; i < needle.length(); i++) {
            hashHaystack = hashHaystack * 26 + (long) (haystack.charAt(i) - 'a');
        }


        if (hashHaystack == hashNeedle) {
            return 0;
        }

        for (int i = needle.length(); i < haystack.length(); i++) {
            hashHaystack -= (long) (haystack.charAt(i - needle.length()) - 'a') * (long) Math.pow(26, needle.length() - 1);
            hashHaystack *= 26;
            hashHaystack += (long) (haystack.charAt(i) - 'a') ;

            if (hashHaystack == hashNeedle) {
                return i - needle.length() + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        StrStr s = new StrStr();
        int i = s.strStr("mississippi", "issi");
        System.out.println(i);
    }
}
