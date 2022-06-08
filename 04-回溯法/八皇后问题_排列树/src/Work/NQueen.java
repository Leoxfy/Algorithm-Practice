package Work;

public class NQueen {
    static int n;
    static int[] arr;
    static long sum;

    public static void main(String[] args) {
        new NQueen(8);
    }
    NQueen(int nn){
        n = nn;
        arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = i+1;
        }
        dfs(0);
        System.out.println("n皇后【排列树】共有"+sum+"个解。");
    }

    private void dfs(int k) {
        if(k==n){
            sum++;
            printSolve();
            return ;
        }
        for(int i=k;i<n;i++){
            int t = arr[i];arr[i] = arr[k];arr[k] = t;

            //剪枝 约束函数+限界函数
            if(check(k)){
                dfs(k+1);
            }

            t = arr[i];arr[i] = arr[k];arr[k] = t;
        }
    }

    private static void printSolve() {
        System.out.println("["+sum+"]");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i]==j){
                    System.out.print("Q  ");
                }else{
                    System.out.print("x  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    boolean check(int k){
        for(int j=0;j<k;j++){
            if(Math.abs(k-j)==Math.abs(arr[j]-arr[k]) || arr[j]==arr[k]){
                return false;
            }
        }
        return true;
    }
}
