package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2131. Longest Palindrome by Concatenating Two Letter Words
 * https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/
 */
//2133
//2157
//TODO 문제에 대한 이해를 부족하게 했다. 동일 문자가 앞서서 여러번 나올 수 있다. HashMap 문제
public class LongestPalindromebyConcatenatingTwoLetterWords {

    @Test
    void test(){
        LongestPalindromebyConcatenatingTwoLetterWords word = new LongestPalindromebyConcatenatingTwoLetterWords();
        Assertions.assertEquals(6, word.longestPalindrome(new String[]{"lc","cl","gg"}));
        Assertions.assertEquals(8, word.longestPalindrome(new String[]{"ab","ty","yt","lc","cl","ab"}));
        Assertions.assertEquals(2, word.longestPalindrome(new String[]{"cc","ll","xx"}));
        Assertions.assertEquals(14, word.longestPalindrome(new String[]{"qo","fo","fq","qf","fo","ff","qq","qf","of","of","oo","of","of","qf","qf","of"}));
    }
    public int longestPalindrome(String[] words) {
        //2자로 이루어짐
        // 홀수로 중간에 넣을 필요는 없다.
        //permutation은 10^5이기 때문에 어렵다.
        //greedy로 가능할까?

        //case1: 두 char가 다른 경우
        // 'ab' 'bc' <- 이런식으로  ab(xxxx)bc <- 계속해서 붙일 수 있다.
        // length만 알면 됨으로 pair가 된는 경우 모두 길이로 만들자.
        // 예를 들어 'ab'를 set하고 bc가 ab있는지 확인 후 있다고 하면 set을 지우고 +4를 하자.

        //case2 : 두 char가 같은 경우 pair가 없는 경우가 있다면 +2를 하자.

        //"qo","ff","of","of","oo","qf","of"
        Map<String,Integer> set = new HashMap();

        int result = 0;
        int sameCount = 0;
        for(String word : words){
            String reverse = word.charAt(1) + "" + word.charAt(0);
            if(set.containsKey(reverse) && 0 < set.get(reverse)) {
                if(word.charAt(0) == word.charAt(1)) sameCount--;
                set.put(reverse, set.get(reverse) - 1);
                result += 4;
            } else {
                if(word.charAt(0) == word.charAt(1)) sameCount++;
                set.put(word, set.getOrDefault(word,0) + 1);
            }
        }
        if(0 < sameCount) result+=2;

        return result;
    }
}
