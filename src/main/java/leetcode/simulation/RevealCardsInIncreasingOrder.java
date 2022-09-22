package leetcode.simulation;

import leetcode.math.UglyNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 950. Reveal Cards In Increasing Order
 * https://leetcode.com/problems/reveal-cards-in-increasing-order/
 */
public class RevealCardsInIncreasingOrder {
    @Test
    void test(){
        RevealCardsInIncreasingOrder order = new RevealCardsInIncreasingOrder();

        Assertions.assertArrayEquals(new int[]{2,13,3,11,5,17,7}, order.deckRevealedIncreasing(new int[]{17,13,11,2,3,5,7}));
        Assertions.assertArrayEquals(new int[]{1,3,2}, order.deckRevealedIncreasing(new int[]{1,2,3}));
        Assertions.assertArrayEquals(new int[]{1,6,2,5,3,7,4}, order.deckRevealedIncreasing(new int[]{1,2,3,4,5,6,7}));

    }

    public int[] deckRevealedIncreasing(int[] deck) {
        //Take the top card of the deck, reveal it, and take it out of the deck.
        //If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.
        //If there are still unrevealed cards, go back to step 1. Otherwise, stop.
        if(deck.length < 3) return deck;


        Arrays.sort(deck);
        int end = deck.length - 3;
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(deck[deck.length-2]);
        list.addLast(deck[deck.length-1]);

        //1,2,3,4,5,6,7
        //1,6,2,5,3,7,4
        //1 2,5,3,7,4,6
        //1,2 3,7,4,6,5 <-3을 붙히면서 마지막 값을 맨위로 변경
        //1,2,3, 4,6,5,7 <- 4를 붙히면서 마지막 값을 맨위로 변경
        //1,2,3,4, 5,7,6 <- 5를 붙히면서 마지막 값을 맨위로 변경
        //1,2,3,4,5, 6,7

        while (0 <= end) {
            int pop = deck[end];
            end--;

            list.addFirst(list.pollLast());
            list.addFirst(pop);
        }

        return list.stream().mapToInt(i->i).toArray();

    }



}
