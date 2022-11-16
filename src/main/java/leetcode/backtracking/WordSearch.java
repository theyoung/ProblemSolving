package leetcode.backtracking;

/*
79. Word Search
https://leetcode.com/problems/word-search/?envType=study-plan&id=level-3
 */
//2330
//2340
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if(dfs(row,col,board,0,word, new boolean[board.length][board[0].length])) return true;
            }
        }
        return false;
    }

    final int[][] move = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    boolean dfs(int row, int col, char[][] board,int index, String word, boolean[][] visited){
        if(row < 0 || col < 0 || board.length <= row || board[0].length <= col) return false;
        if(word.length()-1 == index){
            return board[row][col] == word.charAt(index);
        }

        visited[row][col] = true;
        if(board[row][col] == word.charAt(index)){
            for(int[] next : move){
                if (dfs(row + next[0], col + next[1], board, index + 1, word, visited)) {
                    return true;
                }
            }
        }
        visited[row][col] = false;

        return false;
    }
}
