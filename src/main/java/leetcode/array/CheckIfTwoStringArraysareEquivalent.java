package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 1662. Check If Two String Arrays are Equivalent
 * https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 */
//2314
//2318
public class CheckIfTwoStringArraysareEquivalent {
    @Test
    void test(){

        CheckIfTwoStringArraysareEquivalent equivalent = new CheckIfTwoStringArraysareEquivalent();
        Assertions.assertEquals(true,equivalent.arrayStringsAreEqual(new String[]{"a","b","cdef"}, new String[]{"abc","def"}));
    }
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder wordA = new StringBuilder();
        StringBuilder wordB = new StringBuilder();

        for(String word : word1) wordA.append(word);
        for(String word : word2) wordB.append(word);

        return wordA.toString().equals(wordB.toString());
    }
}
