package banzi;

public class MagicDictionary {

    private TireNode root = new TireNode();

    public MagicDictionary() {

    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            root.insert(s);
        }
    }

    public boolean search(String searchWord) {
        return root.search(searchWord);
    }
}

class TireNode {
    TireNode[] children = new TireNode[26];
    boolean isEnd;


    public void insert(String word) {
        TireNode node = this;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) {
                node.children[i] = new TireNode();
            }
            node = node.children[i];
        }
        node.isEnd = true;
    }
    
    public boolean search(String searchWord) {
        return dfs(searchWord,0,this,0);
    }

    private boolean dfs(String searchWord, int i, TireNode tireNode, int diff) {
        if (i == searchWord.length()) {
            return tireNode.isEnd && diff == 1;
        }

        int j = searchWord.charAt(i) - 'a';

        // 有值
        if (tireNode.children[j] != null) {
            if (dfs(searchWord,i+1,tireNode.children[j],diff)) {
                return true;
            }
        }
        // 无值
        if (diff == 0){
            for (int k = 0; k < 26; k++) {
                if (k != j && tireNode.children[k] != null) {
                    if (dfs(searchWord, i + 1, tireNode.children[k], 1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


}
