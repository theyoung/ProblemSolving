package leetcode.stack;

import java.util.LinkedList;

/*
224. Basic Calculator
https://leetcode.com/problems/basic-calculator/
 */
//TODO 음수처리와 열림 중괄호의 갯수가 중요하다.
public class BasicCalculator {
    public int calculate(String s) {
        s = s.replaceAll(" ","");

        LinkedList<Integer> nums = new LinkedList<>();
        LinkedList<Character> operators = new LinkedList<>();

        char[] chars = s.toCharArray();
        int count = 0;

        int rightBrace = 0;
        for (int i = chars.length-1; 0 <= i; i--) {
            if(isDigit(chars[i])){
                if(0 < count){
                    int last = nums.pollLast();
                    int digit = (int)(Math.pow(10, count) * (chars[i] - '0') + last);
                    count++;
                    nums.addLast(digit);
                } else {
                    count++;
                    nums.addLast(chars[i] - '0');
                }
            } else {
                count = 0;

                if(chars[i] == '('){
                    if(operators.peekLast() == '-' && nums.size() <= operators.size()-rightBrace){
                        nums.addLast(-1 * nums.pollLast());
                        operators.pollLast();
                    }


                    while(!operators.isEmpty() && operators.peekLast() != ')'){
                        int left = nums.pollLast();
                        int right = nums.pollLast();
                        int mergeVal = calc(left,right,operators.pollLast());
                        nums.addLast(mergeVal);
                    }
                    operators.pollLast();// remove ')'
                    rightBrace--;
                } else {
                    if(chars[i] == ')') rightBrace++;
                    operators.addLast(chars[i]);
                    // System.out.println(operators.size());
                }
            }

        }

        // System.out.println(nums.toString());

        if(!operators.isEmpty() && operators.peekLast() == '-' && nums.size() <= operators.size()){
            operators.pollLast();
            nums.addLast(-1 * nums.pollLast());
        }
        while(!operators.isEmpty()){
            int left = nums.pollLast();
            int right = nums.pollLast();
            int mergeVal = calc(left,right,operators.pollLast());
            nums.addLast(mergeVal);
        }

        return nums.pollLast();
    }

    int calc(int a, int b, char oper){
        // System.out.println(a + " " + b + " " + oper);
        if(oper=='-') return a-b;
        return a+b;
    }

    boolean isDigit(char ch){
        if('0' <= ch && ch <= '9'){
            return true;
        }
        return false;
    }
}
