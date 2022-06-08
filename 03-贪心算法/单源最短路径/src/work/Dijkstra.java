package work;

public class Dijkstra {
    public static void main(String[] args) {
        int[][] f = new int [6][6];
        int no = Integer.MAX_VALUE;
        f = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 10, no, 30, 100},
                {0, no, 0, 50, no, no},
                {0, no, no, 0, no, 10},
                {0, no, no, 20, 0, 60},
                {0, no, no, no, no, 0}
        };
        int[] dist = new int[6];
        int[] prev = new int[6];
        dijkstra(1, f, dist, prev);
        System.out.println("源点到各点的距离分别是：");
        for (int i=1;i<=5;i++){
            System.out.print(dist[i]+"\t");
        }
        System.out.println();
        System.out.println("他们的前驱分别是：");
        for (int i=1;i<=5;i++){
            System.out.print(prev[i]+"\t");
        }
    }
    public static void dijkstra(int v, int[][] a, int[] dist, int[] prev){
        //                  v是源点    a是临接矩阵  dist记录距离  prev记录每个点的前驱
        // dist使用1~n 不用0 长度是n+1
        int n = dist.length -1;

        // 特殊情况判断，一般可以不用
        // v应该是在1~n之间的一个点
        if(v<1 || v>n){
            return ;
        }
        boolean[] s = new boolean[n+1];
        // s记录是否属于S集合

        // 初始化 i:1~n
        for(int i=1;i<=n;i++){
            // dist数组
            dist[i] = a[v][i];
            // 访问数组
            s[i] = false;
            // 前驱数组
            if(dist[i]==Integer.MAX_VALUE){
                prev[i] = 0;
            }else{
                prev[i] = v;
            }
        }
        // v到v的距离肯定是0 这句我觉得可以不要
        dist[v] = 0;
        // 标记访问数组
        s[v] = true;
        //做n-1次循环，也就是确定剩余的n-1个点的距离
        for(int i=1;i<n;i++){
            int minDist = Integer.MAX_VALUE;
            //先在剩余集合里找到dist最小的那个点
            int minPoint = v;
            for(int j=1;j<=n;j++){
                if(!s[j] && dist[j]<minDist){
                    minPoint = j;
                    minDist = dist[j];
                }
            }
            //剩余集合中最小的加入S集合，
            s[minPoint] = true;
            //从最小点开始松弛
            for(int j=1;j<=n;j++){
                // 没访问过，并且是和minPoint直接相连的
                if(!s[j] && a[minPoint][j]<Integer.MAX_VALUE){
                    int newDist = dist[minPoint] + a[minPoint][j];
                    if(newDist < dist[j]){
                        dist[j] = newDist;
                        prev[j] = minPoint;
                    }
                }
            }
        }
    }
}
