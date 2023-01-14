package leetcode.string;

/*
1061. Lexicographically Smallest Equivalent String
https://leetcode.com/problems/lexicographically-smallest-equivalent-string/description/
 */
//Union find
public class LexicographicallySmallestEquivalentString {
    int[] chars;
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        chars = new int[26];
        for(int i = 0; i < 26; i++) chars[i] = i;

        for(int i = 0; i < s1.length(); i++){
            int left = s1.charAt(i) - 'a';
            int right = s2.charAt(i) - 'a';
            int leftPosition = find(left);
            int rightPosition = find(right);
            if(leftPosition < rightPosition) chars[rightPosition] = leftPosition;
            else chars[leftPosition] = rightPosition;
        }
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < baseStr.length(); i++){
            int idx = baseStr.charAt(i) - 'a';
            char smallChar = (char)(find(idx) + 'a');
            sb.append(smallChar);
        }
        return sb.toString();
    }

    int find(int target){
        if(chars[target] == target) return target;
        chars[target] = find(chars[target]);
        return chars[target];
    }
}
