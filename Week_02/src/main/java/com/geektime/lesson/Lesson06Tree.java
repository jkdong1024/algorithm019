package com.geektime.lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:     [Week_02]
 * 创建人:     [jkdong]
 * 创建时间:   [2020/11/24 21:55]
 * 版本:       [v1.0]
 */
public class Lesson06Tree {
    public static void main(String[] args) {

    }

    //  二叉树的中序遍历
    //  题目; 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
    //  中序遍历：左-根-右  子树同理
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res =new ArrayList<Integer>();
        inorder(root,res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
    }

    //  二叉树的前序遍历
    //  题目：给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
    //  中序遍历：根-左-右  子树同理
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return  res;
    }
    public void preorder(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.val);
        preorder(root.left,res);
        preorder(root.right,res);
    }

    //  N叉树的后序遍历
    //  题目：给定一个 N 叉树，返回其节点值的后序遍历。
    public List<Integer> postorder(Node root) {

        return null;
    }

}
