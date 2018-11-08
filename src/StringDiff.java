import java.util.*;

/**
*   CASE ONE: Or or one number of edits for string difference
*/

class StringDiff{

    final static String INPUT_ERR = "One or both input args are null.";

    public static void main(String[] args){

        System.out.println(onlyOneEdit("pale", "ple") == true);
        System.out.println(onlyOneEdit("pales", "pale") == true);
        System.out.println(onlyOneEdit("pale", "bale") == true);
        System.out.println(onlyOneEdit("pale", "bake") == false);
        System.out.println(onlyOneEdit("aab", "ba") == false);  // hits a false positive
        System.out.println(onlyOneEdit("aaa", "a") == false);
        System.out.println(onlyOneEdit("aaa", "xyz") == false);
        System.out.println(onlyOneEdit("abcdd", "abcd") == true);
        System.out.println(onlyOneEdit("abc", "abc") == true);

        System.out.println(onlyOneEdit("ba", "aab") == false);

        /*----- MORE TEST CASES FOR COUNTING CHAR DIFF ------*/
        // double counting, a  aaa
        // no intersection, a  b or abc  xyz
        // str length different, abcdd abcd
        // charDiff = 0, abc abc
    }

    // return if only zero or one edit has been made
    static boolean onlyOneEdit(String s1, String s2){
        // handle degenerate cases
        if (s1 == null || s2 == null) { System.err.println(INPUT_ERR); return false; }
        if (s1.equals(s2)) return true;

        // two or more adds or removes done
        int lenDiff = Math.abs(s1.length() - s2.length());
        if (lenDiff > 1) return false;

        // difference in length of 1 is AT LEAST one edit
        int numEdits = 0;
        int i = 0, j = 0;

        for (i = 0, j = 0; i < s1.length() && j < s2.length(); i++, j++){

            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(j);

            //System.out.format("ch1: %s, ch2: %s \n", ch1, ch2);

            // difference detected
            if (ch1 != ch2){
                if (++numEdits > 1) return false;

                // add found, skip j
                if (j+1 < s2.length() && ch1 == s2.charAt(j+1))
                    j++;

                // remove found, skip i
                else if (i+1 < s1.length() && s1.charAt(i+1) == ch2)
                    i++;
            }

        }
        numEdits += s1.length() - i;
        numEdits += s2.length() - j;

        return numEdits <= 1;
    }

}

