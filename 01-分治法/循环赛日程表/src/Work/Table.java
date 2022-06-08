package Work;

import java.util.Scanner;

public class Table {
    static void solveTable(int k, int[][] a){
        int n = 1;
        // 计算n=2^k
        for(int i=1;i<=k;i++){
            n*=2;
        }
        // 第一行赋值为1~n
        for(int i=1;i<=n;i++){
            a[1][i] = i;
        }
        int m = 1;

        for(int s=1;s<=k;s++){
            // s分别控制n除k次；m乘k次
            // s也是问题划分的次数
            n/=2;
            // 就是原问题分割一半
            for(int t=1;t<=n;t++){
                // 每一部分的问题进行单元格的填充
                for(int i=m+1;i<=2*m;i++){
                    // i控制行标
                    for(int j=m+1;j<=2*m;j++){
                        // j控制列标
                        // m控制问题的规模
                        // t控制第几个问题的规模
                        a[i][j+(t-1)*m*2] = a[i-m][j+(t-1)*m*2-m];
                        a[i][j+(t-1)*m*2-m] = a[i-m][j+(t-1)*m*2];
                    }
                }
            }
            m*=2;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入问题的规模：");
        int k = scanner.nextInt();
        int n = (int)Math.pow(2, k);
        int[][] table = new int[n+1][n+1];
        solveTable(k, table);
        System.out.println("\n循环赛日程表问题的解如下：");
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(table[i][j]+"\t");
            }
            System.out.println("");
        }
    }
}
