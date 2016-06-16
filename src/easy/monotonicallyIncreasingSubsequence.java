package easy;

/**
 * Created by rachelren on 2/4/16.
 */
public class monotonicallyIncreasingSubsequence {
    public int missingEle(int[] array, int start, int end){
        int length = array.length;
        int middle = (end + start)/2;

        if (length == 0){
            return -1;
        }
        if (end - start == 0){
            return (middle == array[middle])? length : middle;
        }
        return (middle >= array[middle])? missingEle(array, middle + 1, end) : missingEle(array, start, middle - 1);

    }

    public static void main (String[] args){
        monotonicallyIncreasingSubsequence c = new monotonicallyIncreasingSubsequence();
        int[] array = {0,1,2,3,4};
        System.out.println(c.missingEle(array, 0, array.length - 1));
    }
}
