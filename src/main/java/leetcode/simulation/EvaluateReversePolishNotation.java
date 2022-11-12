package leetcode.simulation;

import java.util.LinkedList;

/**
 * 150. Evaluate Reverse Polish Notation
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/?envType=study-plan&id=level-3
 */
public class EvaluateReversePolishNotation {
    //stack을 2개 사용해보자.

    //     ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
    //    숫자가 두개 연속으로 오면 pop?

    //  22
    public int evalRPN(String[] tokens) {
        LinkedList<String> stack = new LinkedList<>();

        for(int i = tokens.length-1; 0 <= i ; i--) {
            stack.addLast(tokens[i]);

            while(1 < stack.size() && isDigit(stack.get(stack.size()-1)) && isDigit(stack.get(stack.size()-2))){
                int left = Integer.valueOf(stack.pollLast());
                int right = Integer.valueOf(stack.pollLast());
                String operand = stack.pollLast();
                stack.addLast(String.valueOf(formula(operand, left, right)));
            }
        }

        return Integer.valueOf(stack.pollFirst());
    }

    boolean isDigit(String token){
        if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) return false;
        return true;
    }

    int formula(String operator,int left, int right){
        // System.out.println(operator + " " + left + " " + right);
        switch(operator){
            case "+" :
                return left + right;
            case "-" :
                return left - right;
            case "*" :
                return left * right;
            default:
                return left / right;
        }
    }
}
