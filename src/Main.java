import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       int a = in.nextInt();
       int b = in.nextInt();
       int[][] matrix  = new int[a][b];
        in.nextLine();
        for(int i=0;i<a;i++){
            String strLine = in.nextLine();
            Scanner s = new Scanner(strLine);
            int j=0;
            while(s.hasNextInt()){
                matrix[i][j++]=s.nextInt();
            }
        }


       for(int i = 0 ; i < a ; i++){
           for (int j = 0 ; j < b ; j++){
               System.out.printf(String.valueOf(matrix[i][j]));
           }
           System.out.println("");
       }
    }
}
