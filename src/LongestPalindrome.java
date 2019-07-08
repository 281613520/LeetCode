public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        if(s == null) return "";
        if(s.length() == 1) return s;
        int left = 0;
        int max = 0;

        for (int i = 0 ; i < s.length() ; i++){
            int l = i;
            int r = l;
            while(l >=0 && r < s.length() && s.charAt(l)==s.charAt(r)){
                if (r - l >= max && s.charAt(l) == s.charAt(r)){
                    left = l;
                    max = r -l+1;
                }
                l--;
                r++;
            }
            l = i;
            r = l+1;

            while(l >=0 && r < s.length() && s.charAt(l)==s.charAt(r)){
                if (r - l >= max && s.charAt(l) == s.charAt(r)){
                    left = l;
                    max = r -l+1;
                }
                l--;
                r++;
            }
        }
        return s.substring(left,left +max);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("ac"));
    }
}
