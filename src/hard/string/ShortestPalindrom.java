package hard.string;

/**
 * 214. Shortest Palindrome
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 *
 * For example:
 *
 * Given "aacecaaa", return "aaacecaaa".
 *
 * Given "abcd", return "dcbabcd".
 *
 * Solution:
 * We used a trick + KMP table
 *
 * KMP table:
 *
 * Suppose we have a pattern ABABAC
 * Now the prefix table of this pattern is
 * 0 ""
 * 1 A
 * 2 AB
 * 3 ABA
 * 4 ABAB
 * 5 ABABA
 * 6 ABABAC
 *
 * And the max suffix of prefix is
 *      F[i]  i
 * 0 ""
 * 1 ""  0    0
 * 2 ""  0    1
 * 3 A   1    2
 * 4 AB  2    3
 * 5 ABA 3    4
 * 6 ""  0    5
 *
 * Now we denote a table, F, of length(pattern) to store the max suffix of prefix (as shown above).
 *
 * To construct the table, we want to have a index to store the previous index. The details is listed in the comments
 * below.
 *
 * After constructed the table,
 * we want to perform getTable() for string s + # + reverse(s)
 *
 * input:
 * catacb
 *
 * processed:
 * catacb # bcatac
 *
 * Now the KMP table of the processed string is
 * c a t a c b # b c a t a c
 * 0 0 0 0 1 0 0 0 1 2 3 4 5
 *
 * The last entry in the table meant that in the input string, there is a substring of length 5 that is palindrome
 * That is, input(0...4) is a palindrome
 *
 * Hence, we just need to add reverse(5...input length - 1) to the original string to form the shortest palindrome.
 *
 * KMP reference: https://www.topcoder.com/community/data-science/data-science-tutorials/introduction-to-string-searching-algorithms/
 */
public class ShortestPalindrom {
    public String shortestPalindrome(String s) {
        String temp = s + "#" + reverse(s.toCharArray(), 0, s.length() - 1);
        int[] table = getTable(temp);

        //get the maximum palin part in s starts from 0
        return reverse(s.toCharArray(), table[table.length - 1], s.length() - 1) + s;
    }

    public int[] getTable(String s){
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to matched char in prefix part

        int index = 0;
        //skip index 0, we will not match a string with itself
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(index) == s.charAt(i)){
                //we can extend match in prefix and postfix
                table[i] = table[i-1] + 1;
                index ++;
            }else{
                //match failed, we try to match a shorter substring

                //by assigning index to table[i-1], we will shorten the match string length, and jump to the
                //prefix part that we used to match postfix ended at i - 1
                index = table[i-1];

                while(index > 0 && s.charAt(index) != s.charAt(i)){
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index-1];
                }

                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if(s.charAt(index) == s.charAt(i)){
                    //if match, then extend one char
                    index ++ ;
                }

                table[i] = index;
            }

        }

        return table;
    }

    private String reverse(char[] arr, int s, int f) {
        if (s >= arr.length) {
            return "";
        }
        int i = s;
        int j = f;
        while (i < j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
        return new String(arr, s, f + 1 - s);
    }

    public static void main(String[] args) {
        ShortestPalindrom s = new ShortestPalindrom();
        System.out.println(s.shortestPalindrome("aacecaaa"));
    }
}
