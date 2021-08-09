package com.ccg.demo.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class LinkNode {


    /**
     * 链表反转
     */
    public static void revert() {
        Node d = new Node(5, null);
        Node c = new Node(4, d);
        Node b = new Node(3, c);
        Node a = new Node(2, b);
        Node head = new Node(1, a);
        print(head);

        Node pre = null;
        Node cur = head;

        while (cur != null) {
            // next 暂存起来
            Node next = cur.getNext();
            // 修改cur next 为前一个
            cur.setNext(pre);
            // 把当前节点赋给前一个
            pre = cur;
            // 把下一个赋给当前
            cur = next;
        }

        System.out.println("------------");
        print(pre);
    }

    public static void print(Node node) {
        while (true) {
            System.out.println(node.getData());
            if (node.getNext() == null) {
                return;
            }
            node = node.getNext();
        }
    }

    public static void print2(ListNode node) {
        if(node == null) {
            System.out.println("[]");
            return;
        }
        while (true) {
            System.out.print(node.val + ", ");
            if (node.next == null) {
                return;
            }
            node = node.next;
        }
    }

    public static ListNode getListNode(int[] arr){
        ListNode head = new ListNode(-1, null);
        ListNode last = head;
        for (int i : arr) {
            last.next = new ListNode(i, null);
            last = last.next;
        }
        return head.next;
    }

    /**
     * 链表是否存在环状
     */
    public static void hasCycle() {
        Node d = new Node(5, null);
        Node c = new Node(4, d);
        Node b = new Node(3, c);
        Node a = new Node(2, b);
        Node head = new Node(1, a);
        d.setNext(b);

        Node cur = head;
        Node fasterNode = cur;
        Node slowerNode = cur;
        int i = 0;
        while (true) {
            i++;
            if (fasterNode.getNext() == null) {
                System.out.println("不存在环");
                break;
            }
            // 快慢指针
            fasterNode = fasterNode.getNext().getNext();
            slowerNode = slowerNode.getNext();

            if (fasterNode == slowerNode) {
                System.out.println("存在环");
                break;
            }
            cur = cur.getNext();
        }
        System.out.println("循环次数: " + i);
    }

    /**
     * 合并 链表
     *  与一自己写的第一版相比，思考怎样把通过不同的条件组合，分离出最优的组合并进行处理。
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode();
        ListNode newCur = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                newCur.next = l1;
                l1 = l1.next;
            } else {
                newCur.next = l2;
                l2 = l2.next;
            }
            newCur = newCur.next;
        }

        if (l1 != null) {
            newCur.next = l1;
        }
        if (l2 != null) {
            newCur.next = l2;
        }
        return newHead.next;
    }


    public static ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(true){
            if (fast == null || fast.next == null) {
                print2(slow);
                return slow;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
    }

    /**
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 在处理问题 头节点很多时候很重要， 增加处理的便利性
        ListNode h = new ListNode(-1, head);
        ListNode first = h;
        ListNode second = h;
        int i = 0;
        while(true){
            first = first.next;
            if(i > n){
                second = second.next;
            }
            /**
             * 通过画图来处理种情况下关系
             */
            if(first == null){
                // 判断原理， 还需要再算一下 用图 todo
                // 删除头节点
                if (second.val == h.next.val && second.next == null) {
                    ListNode ss = second.next;
                    second.next = null;
                    return ss;
                }
                // 只一个节点 n=1
                // n = 1
                // 常规情况
                second.next = second.next.next;
                return h.next;
            }
            i++;
        }
    }


    public static void main(String[] args) {
        LinkNode a = new LinkNode();
        //revert();
        // hasCycle();
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
        // ListNode l = mergeTwoLists(l1, l2);
        // print2(l);
        // middleNode(l1);
        print2(a.removeNthFromEnd(getListNode(new int[]{1,2, 3}), 3));

    }

}

class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

@Data
@AllArgsConstructor
class Node {
    private int data;
    private Node next;
}
