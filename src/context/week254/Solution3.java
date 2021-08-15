package context.week254;

public class Solution3 {
    static int mod = 1000000007;
    public static int minNonZeroProduct(int p) {
        long res1 = 1L << p;
        long res2 = multiply((res1 - 2), (1L << (p - 1)) - 1);
        return (int) ((((res1 - 1) % mod) * (res2 % mod)) % mod);
    }
    private static long multiply(long base, long k) {
        long res = 1;
        while (k != 0) {
            if (k % 2 == 1) {
                res = ((res % mod) * (base % mod)) % mod;
            }
            base = ((base % mod) * (base % mod)) % mod;
            k /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(minNonZeroProduct(5));
    }
}
