package leetcode.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 212. Word Search II
 * https://leetcode.com/problems/word-search-ii/
 */
//1520
//1605
public class WordSearchII {
    @Test
    void test(){
        WordSearchII searchII = new WordSearchII();
        System.out.println(searchII.findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}}, new String[]{"oath","pea","eat","rain"}).toString());
        System.out.println(searchII.findWords(new char[][]{{'a','b'},{'c','d'}}, new String[]{"abcb"}).toString());
        System.out.println(searchII.findWords(new char[][]{{'a','b'},{'c','d'}}, new String[]{"a","ab","abcde"}).toString());
        System.out.println(searchII.findWords(new char[][]{{'a','a'}}, new String[]{"aaa"}).toString());
    }

    public List<String> findWords(char[][] board, String[] words) {
        //words로 trie를 만들고 word search를 진행 하면 됨
        Trie root = new Trie();

        for(String word : words){
            root.addWord(word,0);
        }
        Set<String> result = new HashSet<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                List<String> results = helper(board, row, col, root,visited);
                if(results == null) continue;
                for(String str : results){
                    result.add(str);
                }
            }
        }

        return new ArrayList<>(result);
    }

    final int[][] loop = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private List<String> helper(char[][] board, int row, int col, Trie root, boolean[][] visited) {
        if(row < 0 || col < 0 || board.length <= row || board[0].length <= col || visited[row][col]) return null;
        char ch = board[row][col];
        Trie trie = root.map.get(ch);
        if(trie == null) return null;
        List<String> result = new ArrayList<>();
        if(trie.isWord) result.add(trie.word);
        visited[row][col] = true;
        for(int[] ar : loop){
            List<String> tmp = helper(board,row+ar[0],col+ar[1],trie, visited);
            if(tmp != null) result.addAll(tmp);
        }
        visited[row][col] = false;
        return result;
    }

    // Root
    //  / \
    // o   a
    class Trie{
        public Map<Character, Trie> map;
        public boolean isWord = false;
        public String word = "";

        public Trie(){
            map = new HashMap<>();
        }

        public void addWord(String word, int index){
            if(word.length() == index){
                isWord = true;
                this.word = word;
                return;
            }
            char ch = word.charAt(index);
            Trie trie = map.getOrDefault(ch, new Trie());
            trie.addWord(word, index+1);
            map.put(ch, trie);
        }

        public String findWord(String word, int index){
            if(word.length() == index){
                return isWord ? word : null;
            }
            char ch = word.charAt(index);
            if(map.get(ch)!=null){
                return map.get(ch).findWord(word, index + 1);
            }
            return null;
        }

    }
}
