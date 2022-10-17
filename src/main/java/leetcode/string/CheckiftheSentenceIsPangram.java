package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 1832. Check if the Sentence Is Pangram
 * https://leetcode.com/problems/check-if-the-sentence-is-pangram/
 */
public class CheckiftheSentenceIsPangram {

    @Test
    void test(){
        CheckiftheSentenceIsPangram pangram = new CheckiftheSentenceIsPangram();

        Assertions.assertTrue(pangram.checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
        Assertions.assertTrue(pangram.checkIfPangram("abcdefghijklmnopqrstuvwxyz"));
        Assertions.assertFalse(pangram.checkIfPangram("abcdefghijklmnopqrstuvwxy"));
    }

    public boolean checkIfPangram(String sentence) {
        int[] alphabets = new int[26];

        for(char ch : sentence.toCharArray()){
            alphabets[ch-'a']++;
        }

        for(int count : alphabets){
            if(count < 1) return false;
        }

        return true;
    }
}
