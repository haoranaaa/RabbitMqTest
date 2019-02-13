package com.dhr.compute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author haoran.duan
 * @since 10 二月 2019
 */
public class AllRoadTree {
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     */
     /**
         * @param root: the root of the binary tree
         * @return: all root-to-leaf paths
         */
    public List<String> binaryTreePaths(TreeNode root) {
        int[] ints = Arrays.copyOf(new int[] { 0, 1 }, 2);
        // write your code here
        List<String> list=new ArrayList<String>();
        if(root==null){
            return list;
        }
        if(root.left==null && root.right==null){
            list.add(""+root.val);
        }
        List<String> left=binaryTreePaths(root.left);
        for(int i=0;i<left.size();i++){
            list.add(""+root.val+"->"+left.get(i));
        }
        List<String> right=binaryTreePaths(root.right);
        for(int i=0;i<right.size();i++){
            list.add(""+root.val+"->"+right.get(i));
        }
        return list;
    }

}
