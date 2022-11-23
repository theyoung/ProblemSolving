package leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//2200
//2237
public class DesignAddandSearchWordsDataStructure {
    Trie root;
    public DesignAddandSearchWordsDataStructure() {
        root = new Trie();
    }

    public void addWord(String word) {
        root.addWord(word);
    }

    public boolean search(String word) {
        return find(root, word, 0);
    }

    public boolean find(Trie node, String word, int i){
        if(word.length()==i && node.end) return true;
        if(word.length()<=i) return false;

        char ch = word.charAt(i);
        if(ch == '.'){
            for(Trie next : node.map.values()){
                if(find(next, word, i+1)) return true;
            }
        } else if(node.map.containsKey(ch)){
            if(find(node.map.get(ch), word, i+1)) return true;
        }
        return false;
    }


    class Trie{
        //시작은 root로 시작한다.
        public Map<Character,Trie> map;
        String word;
        boolean end = false;
        public Trie(){
            map = new HashMap<>();
            word = "";
        }

        public void addWord(String word){
            addWord(word, 0);
        }

        public void addWord(String word, int next){
            if(word.length() == next){
                this.word = word;
                end = true;
                return;
            }

            Trie trie = map.getOrDefault(word.charAt(next), new Trie());
            trie.addWord(word, next+1);
            map.put(word.charAt(next), trie);
        }
    }


    //아래는 LTE가 난다. 최적화가 필요하다.
//    Set<String> set;
//    public DesignAddandSearchWordsDataStructure() {
//        set = new HashSet<>();
//    }
//
//    public void addWord(String word) {
//        set.add(word);
//    }
//
//    public boolean search(String word) {
//        if(set.contains(word)) return true;
//        for(String str : set){
//            boolean same = true;
//            if(str.length() == word.length()){
//                for (int i = 0; i < str.length(); i++) {
//                    if(word.charAt(i) == '.') continue;
//                    if(word.charAt(i) == str.charAt(i)) continue;
//                    else {
//                        same = false;
//                        break;
//                    }
//                }
//                if(same) return true;
//            }
//        }
//        return false;
//    }
}
