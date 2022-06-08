package work;

public class KnapSack01 {
    public static void main(String[] args) {
        int[] w = {2,2,6,5,4};
        int[] v = {6,3,5,4,6};
        int W = 10;
        int[][] c = new int[w.length+1][W+1];
        int maxValue = knapSack(w, v, W, c);
        System.out.println("背包可容纳的最大价值为："+maxValue);
        int[] ans = findGoods(w, c);
        System.out.println("需要放入背包的物品编号为：");
        for(int i=1;i< ans.length;i++){
            if(ans[i]==1){
                System.out.print(i+" ");
            }
        }
    }
    static int knapSack(int[] w, int[] v, int W, int[][] c){
        //把前i件物品装入容量为j的背包
        int m = w.length+1;
        int n = W+1;
        for(int i=0;i<m;i++){
            c[i][0] = 0;
        }
        for(int j=1;j<n;j++){
            c[0][j] = 0;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(j<w[i-1]){
                    c[i][j] = c[i-1][j];
                }else{
                    c[i][j] = Math.max(c[i-1][j], v[i-1]+c[i-1][j-w[i-1]]);
                }
            }
        }
        return c[m-1][n-1];
    }
    static int[] findGoods(int[] w, int[][] c){
        int n = c.length-1;
        int W = c[0].length-1;
        int j = W;
        int[] ans = new int[n+1];
        for(int i=n;i>0;i--){
            if(c[i][j]>c[i-1][j]){
                ans[i] = 1;
                j -= w[i-1];
            }else{
                ans[i] = 0;
            }
        }
        return ans;
    }
}
