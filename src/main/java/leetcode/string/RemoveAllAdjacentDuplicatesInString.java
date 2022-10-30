package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1047. Remove All Adjacent Duplicates In String
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 */
//1827
//1835
public class RemoveAllAdjacentDuplicatesInString {

    @Test
    void test(){
        RemoveAllAdjacentDuplicatesInString str = new RemoveAllAdjacentDuplicatesInString();
        Assertions.assertEquals("c", str.removeDuplicates("aac"));
        Assertions.assertEquals("", str.removeDuplicates("aacc"));
        Assertions.assertEquals("", str.removeDuplicates("acca"));
        Assertions.assertEquals("cb", str.removeDuplicates("caccab"));
        Assertions.assertEquals("ca", str.removeDuplicates("abbaca"));
        Assertions.assertEquals("ay", str.removeDuplicates("azxxzy"));
    }
    public String removeDuplicates(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            stack.addLast(s.charAt(i));
            while(1 < stack.size() && stack.get(stack.size()-1) == stack.get(stack.size()-2)) {
                stack.pollLast();
                stack.pollLast();
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pollFirst());

        return sb.toString();
    }
}
