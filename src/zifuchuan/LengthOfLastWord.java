package zifuchuan;

public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if(s == null) return 0;

        String[] strings = s.split(" ");
        if (strings.length ==0) return 0;
        return strings[strings.length - 1].length();
    }
}
