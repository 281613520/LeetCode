package daily;

import java.util.*;

public class LockingTree {
    private int[] parent;
    private int[] lockNodeUser;
    private List<Integer>[] children;


    public LockingTree(int[] parent) {
        int n = parent.length;
        this.parent = parent;
        this.lockNodeUser = new int[n];
        Arrays.fill(this.lockNodeUser, -1);
        this.children = new List[n];
        for (int i = 0; i < n; i++) {
            this.children[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            int p = parent[i];
            if (p != -1) {
                children[p].add(i);
            }
        }
    }

    public boolean lock(int num, int user) {
        if (lockNodeUser[num] == -1){
            lockNodeUser[num] = user;
            return true;
        }

        return false;
    }

    public boolean unlock(int num, int user) {
        if (lockNodeUser[num] == user){
            lockNodeUser[num] = -1;
            return true;
        }

        return false;
    }

    public boolean upgrade(int num, int user) {
        if (lockNodeUser[num] != -1){
            return false;
        }

        // 1.当前节点为上锁
        // 2.后续节点至少与一个锁了的子孙节点
        // 3.祖先节点没上锁

        List<Integer> childs =  children[num];

        int parentNode = parent[num];

        // dfs祖先节点
        while (parentNode != -1){
            if (lockNodeUser[parentNode] == -1){
                parentNode = parent[parentNode];
            }else {
                return false;
            }
        }

        // dfs儿子节点

        Set<Integer> lockedChildren = new HashSet<>();

        for (Integer child : childs) {
            dfs(child,lockedChildren);
        }

        if (lockedChildren.isEmpty()){
            return false;
        }

        for (Integer lockedChild : lockedChildren) {
            lockNodeUser[lockedChild] = -1;
        }

        lockNodeUser[num] = user;

        return true;
    }

    private void dfs(Integer child, Set<Integer> lockedChildren) {
        if (lockNodeUser[child] != -1){
            lockedChildren.add(child);
        }

        for (Integer ttt : children[child]) {
            dfs(ttt,lockedChildren);
        }

    }
}
