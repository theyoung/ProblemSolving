package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 976. Largest Perimeter Triangle
 * https://leetcode.com/problems/largest-perimeter-triangle/
 */

public class LargestPerimeterTriangle {

    @Test
    void test() {
        LargestPerimeterTriangle triangle = new LargestPerimeterTriangle();

        Assertions.assertEquals(0,triangle.largestPerimeter(new int[]{1,2,3}));
        Assertions.assertEquals(7,triangle.largestPerimeter(new int[]{2,2,3}));
        Assertions.assertEquals(5,triangle.largestPerimeter(new int[]{2,1,2}));
        Assertions.assertEquals(0,triangle.largestPerimeter(new int[]{1,2,1}));
        Assertions.assertEquals(10,triangle.largestPerimeter(new int[]{3,2,3,4}));
    }

    //어짜피 최대 삼각형은 sorting이후에 밀접한 3개를 비교하는 것 말고는 다른 방법은 존재하지 않는다.
    //처음에 너무 어렵게 생각하고 접근한게 패착
    public int largestPerimeter(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().sorted((a, b) -> b - a).collect(Collectors.toList());

        for (int i = 0; i < list.size() - 2; i++) {
            int target = list.get(i);
            if(target < list.get(i+1) + list.get(i+2)) return target + list.get(i+1) + list.get(i+2);
        }

        return 0;
    }


}
