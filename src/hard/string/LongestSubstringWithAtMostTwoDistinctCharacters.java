package hard.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * &&
 * 340. Longest Substring with At Most K Distinct Characters
 *
 * Given a string, find the length of the longest substring T that contains at most 2 (K) distinct characters.
 *
 * For example, Given s = “eceba”,
 * T is "ece" which its length is 3.
 * k = 2
 *
 * Solution:
 * Sliding window solution
 * HashMap stores the character and its last occurrence index.
 * Go through every single character in the string once.
 * For each iteration, put character into the hashmap and then check if the size of the hashmap
 * is greater than k. If yes, we need to shrink the left boundary of the window to the left most index
 * in the hashmap (the entry that has the smallest value). Then remove this entry. And based on this left most
 * index, we can update the global leftmost index (smallest value in hash map + 1) and update the overall len
 * max (current index - global leftmost index + 1).
 *
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s){
        int maxLen, curLen, counter1, counter2;
        maxLen = curLen = counter1 = counter2 = 0;

        HashSet dic = new HashSet();

        if (s.length() < 3){
            return s.length();
        }

        while (counter1 < s.length() - maxLen){
            counter2 = counter1 + 1;
            dic.clear();
            dic.add(s.charAt(counter1));
            while(counter2 < s.length()){
                dic.add(s.charAt(counter2));
                if (dic.size() < 3) {
                    counter2++;
                }else{
                    break;
                }
            }
            curLen = counter2 - counter1;
            if (curLen > maxLen){
                maxLen = curLen;
            }
            counter1++;
        }

        return maxLen;

    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int ret = 0;
        Map<Character, Integer> dict = new HashMap<>();
        int leftMost = 0;
        for (int i = 0; i < s.length(); i++) {
            dict.put(s.charAt(i), i);
            if (dict.size() > k) {
                int localLeft = s.length();
                Character m = null;
                for (Map.Entry e : dict.entrySet()) {
                    if ((Integer) e.getValue() < localLeft) {
                        m = (Character) e.getKey();
                        localLeft = (Integer) e.getValue();
                    }
                }
                leftMost = localLeft + 1;
                dict.remove(m);
            }
            ret = Math.max(ret, i - leftMost + 1);
        }
        return ret;
    }

    public static void main(String[] arg){
        LongestSubstringWithAtMostTwoDistinctCharacters s = new LongestSubstringWithAtMostTwoDistinctCharacters();
        System.out.println("Running!");
        int result = s.lengthOfLongestSubstringTwoDistinct("abc");
        System.out.println(result);
        System.out.println("End");
    }
}
