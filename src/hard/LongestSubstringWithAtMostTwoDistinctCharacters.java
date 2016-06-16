package hard;

import java.util.HashSet;

/**
 * Created by rachelren on 12/10/15.
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

    public static void main(String[] arg){
        LongestSubstringWithAtMostTwoDistinctCharacters s = new LongestSubstringWithAtMostTwoDistinctCharacters();
        System.out.println("Running!");
        int result = s.lengthOfLongestSubstringTwoDistinct("abc");
        System.out.println(result);
        System.out.println("End");
    }
}
