package medium.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 280. Wiggle Sort
 *
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSort {
    public static String wiggleSort(Integer[] even, Integer[] odd, int len) {
        StringBuilder result = new StringBuilder();

        //sort
        Arrays.sort(even, new AssendingComparator());
        Arrays.sort(odd, new DessendingComparator());

        //print
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                result.append(even[i/2]);
                result.append(" ");
            } else {
                result.append(odd[i/2]);
                result.append(" ");
            }
        }
        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

    static class AssendingComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            if (a.equals(b)) {
                return 0;
            }
            return (a < b) ? -1 : 1;
        }
    }

    static class DessendingComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            if (a.equals(b)) {
                return 0;
            }
            return (a > b) ? -1 : 1;
        }
    }


    public static void main(String args[]){
        Scanner cin = new Scanner(System.in);
        String[] temp;
        while (cin.hasNext()){
            temp = cin.nextLine().split(" ");
            if (temp.length == 1) {
                System.out.println(temp[0]);
            } else if (temp.length == 0) {
                System.out.println("");
            } else {
                Integer[] even = new Integer[temp.length/2];
                Integer[] odd = new Integer[temp.length - even.length];
                int evenCount = 0;
                int oddCount = 0;
                for (int i = 0; i < temp.length; i++) {
                    int t = Integer.parseInt(temp[i]);
                    if (i % 2 == 0) {
                        even[evenCount] = t;
                        evenCount++;
                    } else {
                        odd[oddCount] = t;
                        oddCount++;
                    }
                }

                System.out.println(wiggleSort(even, odd, temp.length));
            }
        }

    }

}
