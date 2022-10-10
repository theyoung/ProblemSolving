package leetcode.string;

import org.junit.jupiter.api.Test;

/**
 * 1328. Break a Palindrome
 * https://leetcode.com/problems/break-a-palindrome/
 */
public class BreakaPalindrome {
    // aaa
    // aba -> edge
    // aabaa
    // aaabaaa
    // bab
    // abba

    @Test
    void test(){
        BreakaPalindrome palindrome = new BreakaPalindrome();

        System.out.println(palindrome.breakPalindrome("aaa"));
        System.out.println(palindrome.breakPalindrome("aba"));
        System.out.println(palindrome.breakPalindrome("aca"));
        System.out.println(palindrome.breakPalindrome("abccba"));
        System.out.println(palindrome.breakPalindrome("a"));
    }

    //이게 최적화임
    // 절반까지만 확인해서 a가 아닌게 있다면 무조건 a하는게 답이 된다.
    // 절반전까지 변화가 안생길 경우 주어진 값의 마지막이 b인게 사적순서 최하위가 된다.
    public String breakPalindrome(String palindrome) {
        if(palindrome.length()==1) return "";
        char[] arr = palindrome.toCharArray();

        for (int i = 0; i < (arr.length / 2); i++) {
            if(arr[i] != 'a'){
                arr[i] = 'a';
                return String.valueOf(arr);
            }
        }
        arr[arr.length-1] = 'b';
        return String.valueOf(arr);
    }


    //이하 풀이 법은 복잡하게 풀었음
    //palindrome의 절반까지만 보고 최적화 가능
    public String breakPalindrome2(String palindrome) {
        if(palindrome.length()==1) return "";
        int[] alphas = new int[26];
        char[] arr = palindrome.toCharArray();
        char[] arr2 = palindrome.toCharArray();

        boolean change = false;
        for(int i = 0; i < arr.length; i++){
            char ch = arr[i];
            alphas[ch-'a']++;
            if(ch != 'a' && change == false){
                arr2[i] = 'a';
                change = true;
            }
        }

        if(alphas[0] == palindrome.length() || alphas[0] == palindrome.length()-1){
            arr[arr.length-1] = 'b';
            return String.valueOf(arr);
        }

        return String.valueOf(arr2);
    }

}
