package erchashu;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Trie {
    public class TreeNode{
        private boolean isEnd;
        private Character val;
        private Map<Character,TreeNode> map = new HashMap<>();
    }

    private TreeNode root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root  = new TreeNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode cur = root;
        for (int i = 0; i < word.length() ; i++){
            Character curChar = word.charAt(i);
            if(cur.map.containsKey(curChar)){
                cur = cur.map.get(curChar);
                if (i == word.length() - 1){
                    cur.isEnd = true;
                }
            }else {
                TreeNode treeNode = new TreeNode();
                treeNode.val = curChar;
                if (i == word.length() - 1){
                    treeNode.isEnd = true;
                }
                cur.map.put(curChar,treeNode);
                cur = treeNode;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word.length() == 0) return true;
        TreeNode cur = root;
        for (int i = 0; i < word.length() ; i++){
            Character curChar = word.charAt(i);
            if(cur.map.containsKey(curChar)){
                cur = cur.map.get(curChar);
            }else {
                return false;
            }
        }
        return cur.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix.length() == 0 )return true;
        TreeNode cur = root;
        for (int i = 0; i < prefix.length() ; i++){
            Character curChar = prefix.charAt(i);
            if(cur.map.containsKey(curChar)){
                cur = cur.map.get(curChar);
            }else {
                return false;
            }
        }
        return true;
    }
}
