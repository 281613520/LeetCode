public class IsPalindrome {
    public static boolean isPalindrome(int x) {
        int target = x;
        if (x <0) return false;

        int tmp = 0 ;
        while(target !=0){
            int nnn = target % 10;
            tmp = tmp*10 + nnn;
            target = target/10;
        }

        return tmp == x;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(122));
    }
}
