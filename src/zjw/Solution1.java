package zjw;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println(doExercise("yz"));
        System.out.println(doExercise("abcdefghijk"));
    }

    public static String doExercise(String str){
        int len = str.length();

        int[] a = new int[51];
        a[0] = 1;
        a[1] = 2;
        a[2] = 4;

        for (int i = 3 ; i < len ; i++){
            a[i] = a[i-1]+ a[i-2]+a[i-3];
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < len ; i++){
            char tmp = str.charAt(i);
            char after = (char)(( tmp - 'a' + a[i] )%26 + 'a');
            sb.append(after);
        }

        return sb.toString();
    }
}
