package com.company.liao.test.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**Z字型按层遍历二叉树 #103
 * 
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. 
 * (ie, from left to right, then right to left for the next level and alternate between).

 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * Created by liaoqianwen on 2016/5/31.
 */

public class BinaryTreeZigzagLevelOrderTraversal {
    TreeNode root;

    public static void main(String[] args){
        BinaryTreeZigzagLevelOrderTraversal test = new BinaryTreeZigzagLevelOrderTraversal();

        int[] arr = {10,23,1,4,2,5,9,7,6,3};
        for(int i :arr){
            test.buildTree(test.root, i);
        }

        List<List<Integer>> list = test.zigzagLevelOrder(test.root);
        System.out.println("[");
        for(List<Integer> it: list){
            System.out.print("  [");
            for(int i: it){
                System.out.print(i);
                if(it.indexOf(i) != it.size()-1){
                    System.out.print(",");
                }else{
                    System.out.println("],");
                }
            }
        }
        System.out.print("]");
    }

    class TreeNode{
        TreeNode left,right;
        int val;

        TreeNode(int val){
            this.val = val;
        }
    }

    /**Z字型遍历二叉树，先从左往右边，然后从右往左，循环
     * @author liaoqianwen
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> res  = new ArrayList<List<Integer>>();
        if(root == null)
            return res;
        LinkedList <TreeNode> currLevel = new LinkedList<TreeNode>();
        currLevel.add(root);
        boolean leftToRight = true;

        while(currLevel.size() > 0)
        {
            ArrayList<Integer> currList = new ArrayList<Integer>();
            LinkedList<TreeNode> nextLevel = new LinkedList <TreeNode>();
            while(currLevel.size() > 0)
            {
                TreeNode node = currLevel.poll();
                if(node.left != null)
                    nextLevel.add(node.left);
                if(node.right != null)
                    nextLevel.add(node.right);
                if(leftToRight)
                    currList.add(node.val);
                else
                    currList.add(0,node.val);  // 指定ArrayList位置添加元素，该位置及后面的元素往后移动一个位置
            }
            res.add(currList);
            currLevel = nextLevel;
            leftToRight = !leftToRight;
        }
        return res;
    }

    /**
     * 递归创建二叉树（构建的是一棵排序二叉树）
     * @param node
     * @param data
     */
    public void buildTree(TreeNode node,int data){
        if(root == null){
            root = new TreeNode(data);
        }else{
            if(data < node.val){
                if(node.left == null){
                    node.left = new TreeNode(data);
                }else{
                    buildTree(node.left,data);
                }
            }else{
                if(node.right == null){
                    node.right = new TreeNode(data);
                }else{
                    buildTree(node.right,data);
                }
            }
        }
    }
}
