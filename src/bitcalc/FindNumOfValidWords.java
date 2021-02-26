package bitcalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 1178. 猜字谜
 *
 *外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 *
 * 字谜的迷面puzzle 按字符串形式给出，如果一个单词word符合下面两个条件，那么它就可以算作谜底：
 *
 * 单词word中包含谜面puzzle的第一个字母。
 * 单词word中的每一个字母都可以在谜面puzzle中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及"based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。
 * 返回一个答案数组answer，数组中的每个元素answer[i]是在给出的单词列表 words 中可以作为字谜迷面puzzles[i]所对应的谜底的单词数目。
 *
 * 
 *
 * 示例：
 *
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"], 
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa" 
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为"absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为"actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为"gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 * 
 *
 * 提示：
 *
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j]都是小写英文字母。
 * 每个puzzles[i]所包含的字符都不重复。
 *
 * 
 */
public class FindNumOfValidWords {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<>();

        List<Integer> res = new ArrayList<>();

        for (String curString : words) {
            int mask = 0;
            for (int i = 0; i < curString.length(); i++) {
                mask |= 1 << (curString.charAt(i) - 'a');
            }

            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        for (String puzzle : puzzles) {
            int total = 0;

            /**
             * 枚举子集的思路
             * 1.由于这个只有7个数字，第一个又要保留，因此只要枚举剩下的6个
             * 那么就有000000 到 111111 这些可能
             * 2.枚举这些可能，组装好第一个数字 即为所要枚举的值
             * 3.累加
             */
            for (int choose = 0 ; choose < (1<<6) ; choose++){
                int mask = 0;
                for (int i = 0 ; i < 6 ; i++){
                    if ((choose & (1<<i)) != 0){
                        mask |= 1<< (puzzle.charAt(i+1) -'a');
                    }
                }

                mask |= 1<< (puzzle.charAt(0) -'a');
                if (frequency.containsKey(mask)){
                    total += frequency.get(mask);
                }
            }
            res.add(total);
        }

        return res;
    }
}
