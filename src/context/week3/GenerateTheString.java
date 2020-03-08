package context.week3;

public class GenerateTheString {
    public static String generateTheString(int n) {
        if(n % 2 != 0){
            StringBuilder sb = new StringBuilder();
            while (n >0){
                sb.append("a");
                n--;
            }
            return sb.toString();

        }else {
            StringBuilder sb = new StringBuilder();
            int count = n-1;
            while (count >0){
                sb.append("a");
                count--;
            }
            return sb.append("b").toString();
        }

    }

    public static void main(String[] args) {
        System.out.println(generateTheString(5));
    }
}
