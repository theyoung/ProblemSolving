package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */
//2320
//0000
//각 array의 elements는 선택될 수도 있고 선택 되지 않을 수 있다. 2가지의 경우의 수를 갖음으로 O(2^N)이 된다.
//하기 문제를 dp로 최적화 가능한지 생각을 많이 해봤지만 앞선 결과가 이후에 오는 결과에 영향을 주게 되어서 dp를 사용하지 못했는데,
//답을 봐도 별 방법이 없는 것으로 보인다.
//TODO bit operation과 bottomup으로 변경 가능하다.
public class MaximumLengthofaConcatenatedStringwithUniqueCharacters {
    @Test
    void test(){
        MaximumLengthofaConcatenatedStringwithUniqueCharacters unique = new MaximumLengthofaConcatenatedStringwithUniqueCharacters();

        Assertions.assertEquals(4, unique.maxLength(List.of("un","iq","ue")));
        Assertions.assertEquals(6, unique.maxLength(List.of("cha","r","act","ers")));
        Assertions.assertEquals(26, unique.maxLength(List.of("abcdefghijklmnopqrstuvwxyz")));
        Assertions.assertEquals(0, unique.maxLength(List.of("aa","bb")));
    }

    public int maxLength(List<String> arr) {
        // 순서가 있는 permutation 처리 하는 방법이 있다.
        // n*(n!/(n-select))이 필요한데, dp이용해서 처리 가능할 것 같다.
        // size가 16이라

        // 두번째는 bit operation을 이용해서 처리하는데 이것도 결국 모든 경우의 수를 찾아야 해서
        // permutation 처리하자
        int max = 0;
        for(int i = 0; i < arr.size(); i++){
            max = Math.max(max, helper(arr, i, new int[26], 0));
        }

        return max;
    }

    int helper(List<String> arr, int startIdx, int[] viewed, int preLen){
        if(arr.size() <= startIdx) return preLen;
        //나를 선택 할 수 있고, 선택하지 않을 수 있다.
        //앞서 viewed가 있다면 나를 선택하면 안된다. 나를 빼고 뒤로 넘긴다.
        String cur = arr.get(startIdx);
        int[] store = new int[26];
        for (int i = 0; i < 26; i++) {
            store[i] = viewed[i];
        }

        for (char ch : cur.toCharArray()) {
            if(0 < viewed[ch-'a'] || 0 < store[ch-'a']) {
                //나는 선택되면 안된다.
                return preLen;
            } else {
                store[ch-'a']++;
            }
        }
        preLen += cur.length();
        int result = preLen;
        //viewed가 없다면 나를 선택하면 된다. 이 경우 내 다음을 선택해서 처리할 수 있다.
        for (int i = startIdx + 1; i < arr.size(); i++) {
            result = Math.max(result, helper(arr, i, store, preLen));
        }
        return result;
    }

}
