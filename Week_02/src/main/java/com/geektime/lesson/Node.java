package com.geektime.lesson;

import java.util.List;

/**
 * 类描述:     [Week_02]
 * 创建人:     [jkdong]
 * 创建时间:   [2020/11/24 22:24]
 * 版本:       [v1.0]
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
