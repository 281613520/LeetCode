package context.week3;

/**
 * 房间中有 n 枚灯泡，编号从 1 到 n，自左向右排成一排。最初，所有的灯都是关着的。
 * <p>
 * 在 k  时刻（ k 的取值范围是 0 到 n - 1），我们打开 light[k] 这个灯。
 * <p>
 * 灯的颜色要想 变成蓝色 就必须同时满足下面两个条件：
 * <p>
 * 灯处于打开状态。
 * 排在它之前（左侧）的所有灯也都处于打开状态。
 * 请返回能够让 所有开着的 灯都 变成蓝色 的时刻 数目 。
 */
public class NumTimesAllBlue {
    public static int numTimesAllBlue(int[] light) {
        // 0 off 1 on 2 blue
        int[] flag = new int[light.length];
        int res = 0;

        int max = 0;
        for (int i : light) {
            flag[i-1] = 1;
            max = Math.max(i - 1,max);
            res += help(flag,max);
        }

        return res;
    }

    private static int help(int[] flag,int location) {
        boolean f = true;
        for (int m = location;m >=0;m--){
            if (flag[m] == 0){
                f = false;
                break;
            }
        }
        if (f){
            for (int m = location;m >=0;m--){
                flag[m] = 2;
            }
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(numTimesAllBlue(new int[]{2,1,3,5,4}));
    }
}
