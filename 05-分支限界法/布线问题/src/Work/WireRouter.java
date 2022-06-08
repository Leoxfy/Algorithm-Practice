package Work;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
测试数据

10
1 1 1 1 1 1 1 1 1 1
1 0 0 1 0 0 0 1 0 1
1 0 0 1 0 0 0 1 0 1
1 0 0 0 0 1 1 0 0 1
1 0 1 1 1 0 0 0 0 1
1 0 0 0 1 0 0 0 0 1
1 0 1 0 0 0 1 0 0 1
1 0 1 1 1 0 1 1 0 1
1 1 0 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1
2 3
9 9

 */



public class WireRouter {
    static int[][] f; //迷宫
    static int[][] vis;
    static int[][] next;//下一个位置

    static int len;//最短距离
    static boolean flag;//是否有解
    static int sx, sy;//起点
    static int ex, ey;//终点
    static Queue<Point> queue = new LinkedList<>();
    static Stack<Point> stack = new Stack<>();
    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        f = new int[size+1][size+1];
        vis = new int[size+1][size+1];

        for(int i=1;i<=size;i++){
            for(int j=1;j<=size;j++){
                f[i][j] = scanner.nextInt();
            }
        }
        sx = scanner.nextInt();
        sy = scanner.nextInt();
        ex = scanner.nextInt();
        ey = scanner.nextInt();

        //初始化next矩阵
        next = new int[][]{
                {0,1},
                {1,0},
                {0,-1},
                {-1,0}
        };



        // 进队出队
        queue.add(new Point(sx,sy));
        vis[sx][sy] = 1;

        while (!queue.isEmpty()){
            //队头出队
            Point now = queue.element();
            if(now.x==ex && now.y==ey){
                len = vis[ex][ey];
                flag = true;
                System.out.println("find it!");
                System.out.println("长度为"+len);
                break;
            }
            queue.remove();

            //扩展结点进队
            for(int i=0;i<4;i++){

                int newX = now.x+next[i][0];
                int newY = now.y+next[i][1];
                if(newX>=1 && newX<=size && newY>=1 && newY<=size && f[newX][newY]==0 && vis[newX][newY]==0){
                    vis[newX][newY] = vis[now.x][now.y]+1;
                    queue.add(new Point(newX, newY));
                }
            }
        }


        //如果有解，输出解
        if(flag){
            //所有的路径入栈
            Point now = new Point(ex,ey);
            stack.push(now);
            for(int i=len-1;i>=1;i--){
                for(int j=0;j<4;j++){
                    int newX = now.x+next[j][0];
                    int newY = now.y+next[j][1];
                    if(vis[newX][newY]==i){
                        now = new Point(newX, newY);
                        stack.push(now);
                        break;
                    }
                }
            }

            //输出栈内元素
            while(!stack.isEmpty()){
                System.out.println(stack.pop());
            }
        }

    }
}
