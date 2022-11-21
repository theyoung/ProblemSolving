package leetcode.matrix;

import java.util.LinkedList;

/*
1926. Nearest Exit from Entrance in Maze
https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
 */
//2234
//2254
public class NearestExitfromEntranceinMaze {

    final int[][] move = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    public int nearestExit(char[][] maze, int[] entrance) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addFirst(entrance);
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        int count = 0;
        while(!queue.isEmpty()){

            for (int i = queue.size() - 1; 0 <= i; i--) {
                int[] next = queue.getFirst();
                visited[next[0]][next[1]] = true;
                if(next[0] == 0 || next[0] == maze.length-1 || next[1] == 0 || next[1] == maze[0].length-1) return count;

                for(int[] mo : move){
                    int y = next[0];
                    int x = next[1];

                    if(y < 0 || x < 0 || maze.length <= y || maze[0].length <= x) continue;
                    if(maze[y][x] == '+' || visited[y][x]) continue;
                    queue.addLast(new int[]{y,x});
                }
                count++;
            }
        }
        return -1;
    }
}
