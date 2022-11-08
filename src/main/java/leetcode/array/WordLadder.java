package leetcode.array;

import java.util.LinkedList;
import java.util.List;

/**
 * 127. Word Ladder
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        LinkedList<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int depth = 0;

        // 10 -1 1
        boolean[] mask = new boolean[wordList.size()];

        while(!queue.isEmpty()){
            depth++;

            for(int i = queue.size()-1; 0 <= i; i--){
                String word = queue.pollFirst();
                if(word.equals(endWord)) return depth;

                for (int j = 0; j < wordList.size(); j++) {
                    if(!mask[j]){
                        if(diff(word, wordList.get(j))){
                            queue.add(wordList.get(j));
                            mask[j] = true;
                        }
                    }
                }
            }
        }
        return 0;

    }

    boolean diff(String a, String b){
        boolean one = false;

        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                if(one) return false;
                one = true;
            }
        }
        return true;
    }
}


