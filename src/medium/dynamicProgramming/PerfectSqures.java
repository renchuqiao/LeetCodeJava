package medium.dynamicProgramming;

/**
 * 279. Perfect Squares
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example,
 * given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class PerfectSqures {

    public static void printArray(int[] a){
        String ret = "";
        for (int i : a){
            ret += i;
            ret += ",";
        }
        System.out.println(ret);
    }
    public static void main(String[] args){
        PerfectSqures p = new PerfectSqures();
    }
}
