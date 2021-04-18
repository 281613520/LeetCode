package context.week237;

import java.util.HashSet;
import java.util.Set;

public class Solution1 {
    public boolean checkIfPangram(String sentence) {
        Set<Character> set = new HashSet<>();
        for (int i = 0 ; i < sentence.length() ; i++){
            if (set.size() == 26) return true;
            set.add(sentence.charAt(i));
        }

        return set.size() == 26;
    }
}
