package easy;

/**
 * Created by rachelren on 1/20/16.
 */
public class myAtoi {
    public int myAtoi(String str) {
        if (str.length() == 0) {
            return 0;
        }
        boolean valid = true;
        int j = 0;
        //Trunct all the space from the string
        while (j < str.length()) {
            if (str.charAt(j) == ' ' && j != str.length() - 1) {
                str = str.substring(0, j) + str.substring(j + 1);
            } else if (str.charAt(j) == ' ' && j == str.length() - 1) {
                str = str.substring(0, j);
            }else{
                j++;
            }
        }
        if (str.equals("")) {
            return 0;
        }
        System.out.println("String is " + str);
        boolean neg = (str.charAt(0) == '-');
        int result = 0;
        int tenth = 0;
        for (int i = str.length() - 1; i >= 0; i--){
            if (i == 0){
                if (!neg) {
                    if ((int) str.charAt(i) >= 48 && (int) str.charAt(i) <= 57){
                        result += (int) Math.pow(10, tenth) * Character.getNumericValue(str.charAt(0));
                    }else if ((int) str.charAt(i) == 43){
                        continue;
                    }
                    else{
                        valid = false;
                        break;
                    }
                }
            }else {
                if ((int) str.charAt(i) >= 48 && (int) str.charAt(i) <= 57) {
                    result += (int) Math.pow(10, tenth) * Character.getNumericValue(str.charAt(i));
                    tenth += 1;
                } else {
                    valid = false;
                    break;
                }
            }
        }
        if (!valid){
            return 0;
        }
        return (neg) ? 0 - result : result;
    }

    public static void main(String[] args){
        myAtoi atoi = new myAtoi();
        System.out.println(atoi.myAtoi("    010"));
    }
}
