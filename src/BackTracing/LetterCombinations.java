package BackTracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LetterCombinations {
    static Map<Character, String> phone = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public static List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();
        if (digits.length() == 0){
            return res;
        }
        help(digits, res, "");
        return res;
    }


    private static void help(String str, List<String> res, String tmp) {
        if (str.length() == 0) {
            res.add(tmp);
        }

        for (int i = 0; i < str.length(); i++) {
            String letters = phone.get(str.charAt(i));
            for (int j = 0; j < letters.length(); j++) {
                if (i == 0) {
                    tmp = tmp + letters.charAt(j);
                    help(str.substring(i + 1), res, tmp);
                    tmp = tmp.substring(0, tmp.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("789"));
    }
}
