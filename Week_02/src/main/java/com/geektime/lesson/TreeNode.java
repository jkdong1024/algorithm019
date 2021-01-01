package com.geektime.lesson;

/**
 * 类描述:     [Week_02]
 * 创建人:     [jkdong]
 * 创建时间:   [2020/11/24 21:57]
 * 版本:       [v1.0]
 */
public class TreeNode {

    int val; // 根
    TreeNode left;  //  左子树
    TreeNode right; //  右子树
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
