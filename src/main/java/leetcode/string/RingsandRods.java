package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 2103. Rings and Rods
 * https://leetcode.com/problems/rings-and-rods/
 */
public class RingsandRods {
    @Test
    void test(){
        RingsandRods ringsandRods = new RingsandRods();

        Assertions.assertEquals(1,ringsandRods.countPoints("B0B6G0R6R0R6G9"));
        Assertions.assertEquals(1,ringsandRods.countPoints("B0R0G0R9R0B0G0"));
        Assertions.assertEquals(0,ringsandRods.countPoints("G4"));
        Assertions.assertEquals(1,ringsandRods.countPoints("R0G0B0R0G0B0R0G0B0R0G0B0R0G0B0"));
        Assertions.assertEquals(0,ringsandRods.countPoints("R0G0R0G0R0G0R0G0R0G0R0G0R0G0R0G0R0G0"));
        Assertions.assertEquals(0,ringsandRods.countPoints("R0G0R0G0R0G0R0G0R0G0R0G0R0G0R0G0R0G0B1"));
        Assertions.assertEquals(1,ringsandRods.countPoints("R0G0R1G0R0G0R0G0R0G1R0G0R0G0R0G0R0G0B1"));
        Assertions.assertEquals(2,ringsandRods.countPoints("R2G0R1G0R0G2R0G0R0G1R0G0B2G0R0G0R0G0B1"));
    }


    public int countPoints(String rings) {
        int[] rods = new int[10];
        Arrays.fill(rods, 0);

        for (int i = 0; i < rings.length() - 1; i += 2) {
            char color = rings.charAt(i);
            int bit = 0;
            if(color == 'R') bit = 0b1;
            if(color == 'G') bit = 0b10;
            if(color == 'B') bit = 0b100;

            int rod = rings.charAt(i+1) - '0';
            rods[rod] |= bit;
        }
        int result = 0;
        for(int rod : rods){
            if(rod == 0b111) result++;
        }

        return result;
    }
}
