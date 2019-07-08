package Math;

public class Add {
    public static String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder sb = new StringBuilder();

        int add = 0;
        int tmp = 0;
        int avalue,bvalue;

        while (i >= 0 || j >= 0 || add > 0) {
            if (i >= 0) {
                avalue = a.charAt(i) - '0';
                i--;
            }else {
                avalue = 0;
            }

            if (j >= 0) {
                bvalue = b.charAt(j) - '0';
                j--;
            }else {
                bvalue = 0;
            }

            tmp = avalue + bvalue + add;
            int res = tmp % 2;
            add = tmp / 2;
            sb.append(res);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.printf(addBinary("11","1"));
    }
}
