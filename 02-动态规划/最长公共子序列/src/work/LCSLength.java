package work;


import java.util.ArrayList;
import java.util.Collections;

public class LCSLength {
    static ArrayList<Character> ans = new ArrayList<>();
    public static void main(String[] args) {
        char[] x = "ABCBDAB".toCharArray();
        char[] y = "BDCABA".toCharArray();
        int[][] b = new int[x.length+1][y.length+1];
        System.out.println("最长公共子序列的长度是："+getLcsLength(x, y, b));
        getLcs(x.length,y.length, x, b);
        //用Collections.reverse()可以逆序
        Collections.reverse(ans);
        System.out.print("最长公共子序列为：");
        for(Character i:ans){
            System.out.print(i);
        }
    }
    static int getLcsLength(char[] x, char[] y, int[][] b){
        //序列的区间是1-m
        int m = x.length + 1;
        int n = y.length + 1;
        int[][] c = new int [m][n];
        for(int i=0;i<m;i++){
            c[i][0] = 0;
        }
        for(int j=0;j<n;j++){
            c[0][j] = 0;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                //这里有坑，注意x[i]会数组越界
                if(x[i-1]==y[j-1]){
                    //相等的情况
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = 1;
                }else{
                    //下面都是不相等的情况
                    //不相等就是 两个上面和左边的取max
                    c[i][j] = Math.max(c[i][j-1], c[i-1][j]);
                    if(c[i-1][j]>=c[i][j-1]){
                        //从上一行过来的
                        b[i][j] = 2;
                    }else{
                        //从左边过来的
                        b[i][j] = 3;
                    }
                }
            }
        }
        //返回的是x.length和y.length
        return c[m-1][n-1];
    }

    //可以返回一个ArrayList
    //回过去找解
    //对于第二种方案，需要的参数就不是b，而是c根据c[i][j]和c[i-1][j-1], c[i-1][j], c[i][j-1]比较，确定b的值（假想的）
    static void getLcs(int i, int j, char[] x, int[][] b){
        //递归出口
        if(i==0||j==0){
            return ;
        }
        if(b[i][j]==1){
            ans.add(x[i-1]);
            getLcs(i-1,j-1, x, b);
        }else if(b[i][j]==2){
            //从上一行过来的
            getLcs(i-1,j,x,b);
        }else{
            //从左边过来的
            getLcs(i,j-1,x,b);
        }
    }

}
