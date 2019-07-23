package medium.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 93 Restore IP Address
 * Strightforward backtracking
 * Caution: Make sure to check leading zeros!
 */
public class RestoreIpAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        f(s, 0, ret, sb, 0);
        return ret;
    }

    private void f(String s, int l, List<String> ret, StringBuilder sb, int num) {
        int n = s.length();
        if (num == 4) {
            if (l == n) ret.add(sb.deleteCharAt(sb.length()-1).toString());
            return;
        }
        for (int i = 1; i <= Math.min(n - l, 3); i++) {
            String t= s.substring(l, l + i);
            if (isValid(t)) {
                int sbl = sb.length();
                sb.append(t+".");
                num++;
                f(s, l + i, ret, sb, num);
                num--;
                sb.delete(sbl, sb.length());
            }
        }
    }

    private boolean isValid(String t) {
        int i = Integer.parseInt(t);
        return i <= 255 && !(i == 0 && t.length() > 1) && !(t.length() > 1 && t.charAt(0) == '0');
    }
}
