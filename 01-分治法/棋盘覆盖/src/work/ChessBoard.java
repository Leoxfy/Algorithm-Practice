package work;

import java.util.Scanner;

import static java.lang.Math.pow;

public class ChessBoard {
    int tile;
    int size;
    int dr;
    int dc;
    int[][] board;

    ChessBoard(int n, int r, int c){
        tile = 1;
        size = (int) pow(2,n);
        board = new int[size][size];
        dr = r;
        dc = c;
        board[dr][dc] = 0;
    }

    public void solve(int tr, int tc, int dr, int dc, int size){
        //tr, tc是左上角坐标  dr，dc是特殊方块坐标    size是棋盘的大小 2^size
        //设置递归出口
        if(size==1){
            return;
        }
        //如果递归还没有结束的话，骨牌的编号+1，棋盘的大小除以2
        int t = tile++;
        int s = size/2;

        //处理左上角的棋盘
        if(dr<tr+s && dc<tc+s){
            //如果特殊方块在左上角
            //直接把左上角的棋盘看作原问题的子问题
            solve(tr, tc, dr, dc, s);
        }else{
            //如果特殊方块不在左上角
            //左上角棋盘的右下角方块填上t号方块，作为特殊方块，然后递归
            board[tr+s-1][tc+s-1] = t;
            solve(tr, tc, tr+s-1, tc+s-1, s);
        }

        //处理右上角的棋盘
        if(dr<tr+s && dc>=tc+s){
            solve(tr,tc+s, dr, dc, s);
        }else{
            board[tr+s-1][tc+s] = t;
            solve(tr, tc+s, tr+s-1, tc+s, s);
        }

        //处理左下角
        if(dr>=tr+s && dc<tc+s){
            solve(tr+s, tc, dr, dc, s);
        }else{
            board[tr+s][tc+s-1] = t;
            solve(tr+s, tc, tr+s, tc+s-1, s);
        }

        //处理右下角
        if(dr>=tr+s && dc>=tc+s){
            solve(tr+s, tc+s, dr, dc, s);
        }else{
            board[tr+s][tc+s] = t;
            solve(tr+s, tc+s, tr+s, tc+s, s);
        }
    }
    void printChessBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(board[i][j]+"\t");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入问题的规模：");
        int n = scanner.nextInt();
        System.out.print("\n请输入特殊方块的坐标：");
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        System.out.println("\n棋盘覆盖问题的解如下：");
        ChessBoard chessBoard = new ChessBoard(n, i , j);
        chessBoard.solve(0,0,i,j, chessBoard.size);
        chessBoard.printChessBoard();
    }
}
