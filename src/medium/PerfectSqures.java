package medium;

/**
 * Created by rachelren on 6/15/16.
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
