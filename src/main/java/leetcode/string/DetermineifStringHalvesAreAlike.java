package leetcode.string;

import java.util.HashSet;
import java.util.Set;

/*
1704. Determine if String Halves Are Alike
https://leetcode.com/problems/determine-if-string-halves-are-alike/
 */
//1025
public class DetermineifStringHalvesAreAlike {
    Set<Character> set;
    public boolean halvesAreAlike(String s) {
        set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        int left = 0;
        int right = 0;
        // ab cd
        for(int i = 0; i < s.length()/2; i++){
            if(set.contains(s.charAt(i))) left++;
            if(set.contains(s.charAt(i+(s.length()/2)))) right++;
        }
        return left == right;
    }
}
