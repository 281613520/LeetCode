package math;

public class MyPow {
    public double myPow(double x, int n) {
        if (x == 1 || x == 0) {
            return x;
        } else if(n == 0){
            return 1;
        } else {
            double result = 1;
            if(n < 0){
                x = 1/x;
                n = -n;
            }
            while(n != 0){
                if((n & 1) != 0){
                    result = result*x;
                }
                x *= x;
                n >>>= 1;
            }
            return result;
        }

    }
}

