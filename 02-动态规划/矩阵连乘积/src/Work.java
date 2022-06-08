public class Work {
    public static void main(String[] args) {
        new Work();
    }
    Work(){
        problem1();
        //problem2();
    }
    void problem1(){
        int[] p = new int[]{3,2,5,10,2,3};
        //5个矩阵，有6个维度数
        int n = p.length;
        //
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        matrixChain(p, m, s);
        System.out.println("最少乘法次数为"+m[1][n-1]);
        System.out.println("需要断开的位置分别是：");
        traceBack(1, n-1, s);
    }
    void problem2(){
        int[] p = new int[]{30,35,15,5,10,20,25};

        int n = p.length;

        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        matrixChain(p, m, s);
        System.out.println("最少乘法次数为"+m[1][n-1]);
        System.out.println("需要断开的位置分别是：");
        traceBack(1, n-1, s);
    }
    static void matrixChain(int[] p, int[][] m, int[][] s){
        // p数组存的是Ai的维数P_i-1 P_i
        // m就是二维状态数组
        // s记录断开的位置
        int n = p.length-1;
        //n:1~5 0不使用
        //对角线都置0
        for(int i=1;i<=n;i++){
            m[i][i] = 0;
        }
        //对角线的方向，一层一层填
        //整个m矩阵0行0列不使用
        //r控制主对角线上面的每一条对角线 范围是2-n
        for(int r=2;r<=n;r++){
            //i控制的是对角线上的每一个元素范围是n-r+1
            //i同时也是行标
            for(int i=1;i<=n-r+1;i++){
                //初始化第一项
                //j代表的就是i行对应的列标
                int j = i+r-1;
                /*断开位置怎么算？ i-1 k j*/
                // 计算从第一个位置断开 i~i+1 i+1~j
                m[i][j] = p[i-1]*p[i]*p[j] + m[i+1][j];
                s[i][j] = i;
                //k代表了断开的位置 计算每一个断开位置的地方 找最小值
                // i~k k+1~j
                for(int k=i+1;k<j;k++){
                    int t=m[i][k] + p[i-1]*p[k]*p[j] + m[k+1][j];
                    if(t<m[i][j]){
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
    }
    //i-j就是矩阵的下标
    static void traceBack(int i, int j, int[][] s){
        if(i==j){
            return ;
        }
        //先找i~最佳断开位置s[i][j]
        traceBack(i, s[i][j], s);
        //找s[i][j]+1~j
        traceBack(s[i][j]+1, j, s);
        System.out.println(i+":"+s[i][j]+" "+(s[i][j]+1)+":"+j);
    }
}
