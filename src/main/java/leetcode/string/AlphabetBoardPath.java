package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 1138. Alphabet Board Path
 * https://leetcode.com/problems/alphabet-board-path/
 */
//TODO 단순한 문제이지만 z라는 edge case를 주의 해야 한다.
public class AlphabetBoardPath {
    @Test
    void test(){
        AlphabetBoardPath path = new AlphabetBoardPath();
        Assertions.assertEquals("DDR!UURRR!!DDD!", path.alphabetBoardPath("leet"));
        Assertions.assertEquals("RR!DDRR!UUL!R!", path.alphabetBoardPath("code"));
        Assertions.assertEquals("DDDDD!UUUUURRR!DDDDLLLD!", path.alphabetBoardPath("zdz"));
    }
    final String[] board = new String[]{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};

    public String alphabetBoardPath(String target) {
        //target이라는 String의 char위치를 확인하고 현재 위치와의 row, col 관계를
        // U,D,L,R로 분리하면 될것으로 보인다.
        // 각 char의 위치를 저장하자.

        //int[] : row,col
        Map<Character, int[]> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < board.length; row++) {
            String str = board[row];
            for (int col = 0; col < str.length(); col++) {
                char ch = str.charAt(col);
                map.put(ch, new int[]{row,col});
            }
        }

        int[] position = new int[]{0,0};
        for(char ch : target.toCharArray()){
            boolean extra = false;
            if((ch == 'z' && !(position[0] == 5)) || (position[0] == 5 && ch!='z')){
                getControl(map, sb, 'u', position);
                position = map.get('u');
            }
            getControl(map,sb,ch,position);

            position = map.get(ch);
            sb.append("!");
        }

        return sb.toString();
    }

    void getControl(Map<Character, int[]> map, StringBuilder sb, char ch, int[] position){
        int[] tPos = map.get(ch);

        int rowDiff = position[0] - tPos[0];
        if(rowDiff < 0){//t가 더큰 row를 갖고 있다.
            for(;rowDiff < 0;rowDiff++) sb.append("D");
        } else if(0 < rowDiff){
            for(;0 < rowDiff;rowDiff--) sb.append("U");
        }
        int colDiff = position[1] - tPos[1];
        if(colDiff < 0){//t가 더큰 col를 갖고 있다.
            for(;colDiff < 0;colDiff++) sb.append("R");
        } else if(0 < colDiff){
            for(;0 < colDiff;colDiff--) sb.append("L");
        }
    }
}
