package leetcode.math;

/*
223. Rectangle Area
https://leetcode.com/problems/rectangle-area/
 */
//2125
//2150
//TODO 겹치는 것을 계산하는 부분에서 겹치는게 없는 부분 처리하는데 시간이 조금 걸렸다.
public class RectangleArea {


    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        //ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
        //-2,-2,2,2  // 3,3,4,4
        int a = getArea(ax1, ay1, ax2, ay2);
        int b = getArea(bx1, by1, bx2, by2);

        int x1 = Math.max(ax1,bx1);//ax1~ax2와 bx1와 bx2의 overap = 3
        int y1 = Math.max(ay1,by1);//ay1~ay2와 by1과 by2의 overap = 3
        int x2 = Math.min(ax2,bx2); // 2
        int y2 = Math.min(ay2,by2); // 2

        int c = getArea(x1, y1,x2,y2);

        return a+b-c;
    }

    int getArea(int ax1, int ay1, int ax2, int ay2){
        if(ax2 < ax1 || ay2 < ay1) return 0;

        int width = Math.abs(ax2 - ax1);
        int height = Math.abs(ay2 - ay1);

        return width * height;
    }
}
