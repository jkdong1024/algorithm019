package com.geektime.lesson;

import java.util.*;

/**
 * 类描述:     [Lesson03Linked List 实战题目 ： 反转链表   两两交换链表中的节点  环形链表  环形链表 II  K个一组翻转链表  ]
 * 创建人:     [jkdong]
 * 创建时间:   [2020/11/07 20:22:14]
 * 版本:       [v1.0]
 */

class SolutionLesson03LinkedList {

    public static void main(String[] args) {

        int result = 0;
        System.out.println("执行结果为>>>>" + result);
    }

    //  反转链表
    //  题目：反转一个单链表。
    public ListNode reverseList(ListNode head) {

        //  迭代
        /*ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = tmp;
        }
        return prev;*/

        //  递归
        if (head == null || head.next == null){
            return head;
        }
        ListNode resNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return  resNode;
    }

    //  两两交换链表中的节点
    //  题目：给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
    public ListNode swapPairs(ListNode head) {

        //  利用stack
        if (null == head || null == head.next){
            return head;
        }else {

            Stack<ListNode> stack = new Stack<>();
            //  虚拟节点 当前节点初始指向head节点 head指向虚拟节点  此时p指向null
            ListNode p = new ListNode(-1);
            ListNode cur = head;
            head = p;
            while (cur != null && cur.next != null){
                //  当前节点以及下一节点放入stack中
                stack.add(cur);
                stack.add(cur.next);
                //  当前节点向前走两步
                cur = cur.next.next;
                //  stack中弹出两个节点， 此时原来的cur和cur.next顺序已经交换
                p.next = stack.pop();
                p = p.next;
                p.next = stack.pop();
                p = p.next;

            }
            //  控制边界  链表节点为奇数时 cur不为null
            if(cur != null){
                p.next = cur;
            }else {
                p.next = null;
            }
            return head.next;
        }
    }

    //  环形链表
    //  题目：给定一个链表，判断链表中是否有环。
    //  如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    //  如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    //  如果链表中存在环，则返回 true 。 否则，返回 false 。

    public boolean hasCycle(ListNode head) {
        //  快慢指针  龟兔赛跑算法
        if(head == null || head.next == null){
            return false;
        }
        //  初始化
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            //  第一遍时：fast不存在--只有一个节点  或者 fast.next不存在--只有三个节点且第三个节点没有后继节点（即 非三节点最小环）
            //  后续遍历：fast或fast.netx无后继节点  不可能成环
            if (fast == null || fast.next == null){
                return false;
            }
            //  快慢指针不重合 遍历各自下一个节点
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}