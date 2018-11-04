import java.util.*;

/**
*   CASE ONE: Or or one number of edits for string difference
*   Author: Steven Bonilla
*/

class StringDiff{

    final String INPUT_ERR = "One or both input args are null.";

    public static void main(String[] args){

        System.out.println(new StringDiff().isOneEditDifferent("pale", "ple") + "\n");
        System.out.println(new StringDiff().isOneEditDifferent("pales", "pale")+ "\n");
        System.out.println(new StringDiff().isOneEditDifferent("pale", "bale")+ "\n");
        System.out.println(new StringDiff().isOneEditDifferent("pale", "bake")+ "\n");

        /*----- MORE TEST CASES FOR COUNTING CHAR DIFF ------*/
        // double counting, a  aaa
        // no intersection, a  b or abc  xyz
        // str length different, abcdd abcd
        // charDiff = 0, abc abc

        /* I realized it's also possible to iterate over both Strings to detect edits, but it's
            less readable in implementation:

            editsAllowed = 1
            if difference detected
                editsAllowed--;
                insert is when s1 current matches s2 next char
                remove is when s1 next matches s2 current char
                replace is when s1 next == s2 next
        */

    }

    boolean isOneEditDifferent(String s1, String s2){

        // handle degenerate cases
        if (s1 == null || s2 == null) { System.err.println(INPUT_ERR); return false; }
        if (s1.equals(s2)) return true;         // same string

        // build the char-frequency maps for each string
        Map<Character, Integer> map1 = buildFreqMap(s1);
        Map<Character, Integer> map2 = buildFreqMap(s2);

        // count the # of differences
        int totalDiff = 0;
        totalDiff += findCharacterDifference(map1, map2);
        totalDiff += findCharacterDifference(map2, map1);

        // only one add or remove needed
        if (totalDiff == 1) return true;

        // a replace char needed (remove and add), but if strings lengths the same
        // that means a replace was made
        if (totalDiff == 2 && s1.length() == s2.length()) return true;  // was a replace

        // all other cases, return false
        return false;
    }

    int findCharacterDifference(Map<Character, Integer> map1, Map<Character, Integer> map2){
        int difference = 0;
        for (char ch : map1.keySet()){
            int freq1 = map1.get(ch);
            int freq2 = map2.getOrDefault(ch, 0);

            // if map 2 has the char, remove it from consideration to prevent double counting
            if (freq2 != 0){ map2.remove(ch); }
            difference += Math.abs(freq1-freq2);
        }
        //System.out.println(difference);
        return difference;
    }

    Map<Character, Integer> buildFreqMap(String str){
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char ch : str.toCharArray()){
            int freq = map.getOrDefault(ch, 0);
            map.put(ch, ++freq);
        }
        return map;
    }
}

