package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
17. Letter Combinations of a Phone Number
https://leetcode.com/problems/letter-combinations-of-a-phone-number/?envType=study-plan&id=level-3
 */
//2304
//2320
public class LetterCombinationsofaPhoneNumber {

    final List<List<String>> phone = List.of(List.of(),List.of("a","b","c"),List.of("d","e","f"),List.of("g","h","i"),
                                                List.of("j","k","l"), List.of("m","n","o"), List.of("p","q","r","s"), List.of("t","u","v"),
                                                List.of("w","x","y","z")
                                                );

    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return List.of();
        return backtracking(0,digits);
    }

    //backtracking은 종료 조건이 중요하다. 종료 조건부터 생각하자
    //아래는 종료 조건 부터 bottom up으로 올라오는 방식이라면
    //답안에 있는 방식은 top down으로 답을 푼다.
    List<String> backtracking(int index, String digits){
        if(index == digits.length()-1){
            return new ArrayList<>(phone.get(digits.charAt(index)-'1'));
        }

        List<String> list = backtracking(index+1, digits);
        List<String> result = new ArrayList<>();
        for(String start : phone.get(digits.charAt(index)-'1')){
            for(String end : list){
                result.add(start + end);
            }
        }
        
        return result;
    }
}
