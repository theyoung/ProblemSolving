package leetcode.string;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 616. Add Bold Tag in String
 * https://leetcode.com/problems/add-bold-tag-in-string/
 */
//TODO Trie와 Interval Merge를 공부하기 좋다.
public class AddBoldTaginString {
    @Test
    void test(){
        AddBoldTaginString bold = new AddBoldTaginString();

//        System.out.println(bold.addBoldTag("abcdefg",new String[]{"abc","abcd","def"}));
//        System.out.println(bold.addBoldTag("abcxyz123",new String[]{"abc","123"}));
//        System.out.println(bold.addBoldTag("aaabbb",new String[]{"aaabbb","aa","b"}));
//        System.out.println(bold.addBoldTag("a",new String[]{"a","b","c","d","e","f","g"}));
//        System.out.println(bold.addBoldTag("ab",new String[]{"a","b","c","d","e","f","g"}));

//        System.out.println(bold.addBoldTagOptimal("abcdefg",new String[]{"abc","abcd","def"}));
//        System.out.println(bold.addBoldTagOptimal("abcxyz123",new String[]{"abc","123"}));
//        System.out.println(bold.addBoldTagOptimal("aaabbb",new String[]{"aaabbb","aa","b"}));
//        System.out.println(bold.addBoldTagOptimal("a",new String[]{"a","b","c","d","e","f","g"}));
//        System.out.println(bold.addBoldTagOptimal("a",new String[]{"a","b","c","d","e","f","g"}));

        System.out.println(bold.addBoldTagOneD("abcdefg", new String[]{"abc", "abcd", "def"}));
        System.out.println(bold.addBoldTagOneD("abcxyz123", new String[]{"abc", "123"}));
        System.out.println(bold.addBoldTagOneD("aaabbb", new String[]{"aaabbb", "aa", "b"}));
        System.out.println(bold.addBoldTagOneD("a", new String[]{"a", "b", "c", "d", "e", "f", "g"}));
        System.out.println(bold.addBoldTagOneD("ab", new String[]{"a", "b", "c", "d", "e", "f", "g"}));
    }


    public String addBoldTagOptimal(String s, String[] words) {
        int[] index = new int[s.length()+1];

        for (String word: words){
            int i = s.indexOf(word), j;
            while (i != -1){
                j = i + word.length();
                index[i]++;
                index[j]--;
                i = s.indexOf(word, i+1);
            }
        }
        System.out.println(Arrays.toString(index));
        int cur = 0;
        String ans = "";

        for (int i = 0; i < s.length(); ++i){
            if (index[i] > 0 && cur == 0) {
                ans += "<b>";
            }
            if (index[i] < 0 && cur + index[i] == 0){
                ans += "</b>";
            }
            cur += index[i];
            ans += s.charAt(i);
        }
        if (cur > 0) ans += "</b>";
        return ans;
    }


    //Interval을 1D로 변경해 본다.
    public String addBoldTagOneD(String s, String[] words) {
        // s = "abcxyz123"
        // words = ["abc","123"]

        // brute force는 s를 words수만큼 window sliding 하면서 <b></b>를 찾아서 넣으면 된다.
        // words가 1000개 임으로
        // for word : words
        //  s -> sliding word
        // 이러면 word length * s length 1000*1000 = O(10^6)이 된다.

        //Trie로 만들고 가장 긴 값으로 비교하면
        // s length * max(word) 가 된다.

        // 그후 [1,1,-1,-2,,,,,] <- 이런식으로 range를 잡아서 1이 되는 순간 과 0이 되는 순간 <b> </b>를 넣어 주면 된다.
        // 단, 0이 되는 순간 n+1이 1 이라면 연속으로 보고 </B>를 생략한다.
        Trie root = new Trie();
        for (String word : words) {
            root.buildWord(word,0);
        }

        int[] interval = new int[s.length()+1];

        for (int i = 0; i < s.length(); i++) {
            int response = root.find(s, i)-i;
            if(0 <= response){
                interval[i]++;
                interval[i+response+1]--;
            }
        }

        int sum = 0;
        StringBuilder builder = new StringBuilder();

//        System.out.println(Arrays.toString(opens));
//        System.out.println(Arrays.toString(closes));
        for (int i = 0; i < s.length(); i++) {
            if (sum == 0 && 0 < interval[i]) {
                builder.append("<b>");
            }
            if(0 < sum && sum + interval[i] == 0){
                builder.append("</b>");
            }
            sum += interval[i];
            builder.append(s.charAt(i));
        }
        if(0 < sum) builder.append("</b>");

        String result = builder.toString();

        return result;
    }


    //하기 풀이는 너무 어렵다 좀더 쉬운 방법을 찾아보자.
    public String addBoldTag(String s, String[] words) {
        // s = "abcxyz123"
        // words = ["abc","123"]

        // brute force는 s를 words수만큼 window sliding 하면서 <b></b>를 찾아서 넣으면 된다.
        // words가 1000개 임으로
        // for word : words
        //  s -> sliding word
        // 이러면 word length * s length 1000*1000 = O(10^6)이 된다.

        //Trie로 만들고 가장 긴 값으로 비교하면
        // s length * max(word) 가 된다.

        // 그후 [1,1,-1,-2,,,,,] <- 이런식으로 range를 잡아서 1이 되는 순간 과 0이 되는 순간 <b> </b>를 넣어 주면 된다.
        // 단, 0이 되는 순간 n+1이 1 이라면 연속으로 보고 </B>를 생략한다.
        Trie root = new Trie();
        for (String word : words) {
            root.buildWord(word,0);
        }

        int[] opens = new int[s.length()];
        int[] closes = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            int response = root.find(s, i)-i;

            if(0 <= response){
                opens[i]++;
                closes[i+response]--;
            }
        }

        int sum = 0;
        StringBuilder builder = new StringBuilder();

//        System.out.println(Arrays.toString(opens));
//        System.out.println(Arrays.toString(closes));
        for (int i = 0; i < s.length(); i++) {
            if (sum == 0 && 0 < opens[i]) {
                builder.append("<b>");
            }
            sum += opens[i];
            builder.append(s.charAt(i));
            if(0 < sum && sum + closes[i] == 0){
                builder.append("</b>");
            }
            sum += closes[i];
        }

        String result = builder.toString();
        result = result.replaceAll("</b><b>","");

        return result;
    }

    class Trie {
        HashMap<Character, Trie> map;
        boolean isWord = false;

        public Trie(){
            map = new HashMap<>();
        }

        public void buildWord(String word, int index){
            Trie trie = map.getOrDefault(word.charAt(index), new Trie());
            if(word.length() <= index+1){
                trie.isWord = true;
            } else {
                trie.buildWord(word, index+1);
            }
            map.put(word.charAt(index), trie);
        }

        public int find(String word, int index) {
            int result = -1;
            if(index < word.length() && map.containsKey(word.charAt(index))){
                if (map.get(word.charAt(index)).isWord) {
                    result = index;
                }
                result = Math.max(result, map.get(word.charAt(index)).find(word, index + 1));
            }
            return result;
        }
    }
}
