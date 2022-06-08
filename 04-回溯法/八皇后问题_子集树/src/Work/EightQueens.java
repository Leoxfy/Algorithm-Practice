package Work;
// 子集树
public class EightQueens {
    // 皇后个数
    static int n;
    // x保存的是当前的方案
    static int [] x;
    // 当前已经找到的可行方案数
    static long sum;

    public static void main(String[] args) {
        nQueen(8);
        System.out.println("n皇后【子集树】共有"+sum+"个解。");
    }
    static public long nQueen(int nn){
        n = nn;
        sum = 0;
        //  x[i]表示第i行第x[i]列放置
        x = new int[n+1];
        // i使用1~n
        for (int i = 0; i < n+1; i++) {
            x[i] = 0;
        }
        // 从第一层开始放
        dfs(1);
        return sum;
    }

    // 每一层递归
    // t也就是代表当前行
    static private void dfs(int t) {
        if(t==n+1){
            sum++;
            printSolve();
            return ;
        }

        // 第t行尝试1~n种放置方法
        for(int i=1;i<=n;i++){
            x[t] = i;
            // 如果能放，就做下一层，子树做完，t放在下一列上
            // 不能放，直接跳过
            if(place(t)){
                dfs(t+1);
            }
        }
    }

    private static void printSolve() {
        System.out.println("["+sum+"]");
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(x[i]==j){
                    System.out.print("Q  ");
                }else{
                    System.out.print("x  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static private boolean place(int k) {
        for(int j=1;j<k;j++){
            if(Math.abs(k-j)==Math.abs(x[j]-x[k]) || x[j]==x[k]){
                return false;
            }
        }
        return true;
    }

}
