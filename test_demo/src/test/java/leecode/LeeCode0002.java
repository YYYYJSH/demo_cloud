package leecode;

import java.util.ArrayList;
import java.util.List;

public class LeeCode0002 {

    public static class ListNode {
        int val;
        ListNode next;

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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean flag1 = true;
        boolean flag2 = true;
        boolean plus = false;
        List<Integer> list = new ArrayList<>();
        while (flag1 || flag2 || plus) {
            int i = 0;
            if (plus) {
                i = l1.val + l2.val + 1;
            } else {
                i = l1.val + l2.val;
            }
            plus = i >= 10;
            list.add(plus ? i - 10 : i);
            if (null != l1.next) {
                l1 = l1.next;
                flag1 = true;
            } else {
                l1 = new ListNode(0, null);
                flag1 = false;
            }
            if (null != l2.next) {
                l2 = l2.next;
                flag2 = true;
            } else {
                l2 = new ListNode(0, null);
                flag2 = false;
            }
        }

        System.out.println(list);

        ListNode listNode = null;
        for (int i = list.size() ; i > 0; i--) {
            listNode = new ListNode(list.get(i-1), listNode);
        }
        return listNode;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(9, new ListNode(9,null))));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(9, null)));

        ListNode listNode = addTwoNumbers(l1, l2);
        ListNode listNode1 = addTwoNumberss(l1, l2);

        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
        System.out.println("=======================");
        while (null != listNode1) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }

    }




    public static ListNode addTwoNumberss(ListNode l1, ListNode l2) {
        //定义一个新联表伪指针，用来指向头指针，返回结果
        ListNode prev = new ListNode(0);
        //定义一个进位数的指针，用来存储当两数之和大于10的时候，
        int carry = 0;
        //定义一个可移动的指针，用来指向存储两个数之和的位置
        ListNode cur = prev;
        //当l1 不等于null或l2 不等于空时，就进入循环
        while(l1!=null || l2!=null){
            //如果l1 不等于null时，就取他的值，等于null时，就赋值0，保持两个链表具有相同的位数
            int x= l1 !=null ? l1.val : 0;
            //如果l1 不等于null时，就取他的值，等于null时，就赋值0，保持两个链表具有相同的位数
            int y = l2 !=null ? l2.val : 0;
            //将两个链表的值，进行相加，并加上进位数
            int sum = x + y + carry;
            //计算进位数
            carry = sum / 10;
            //计算两个数的和，此时排除超过10的请况（大于10，取余数）
            sum = sum % 10;
            //将求和数赋值给新链表的节点，
            //注意这个时候不能直接将sum赋值给cur.next = sum。这时候会报，类型不匹配。
            //所以这个时候要创一个新的节点，将值赋予节点
            cur.next = new ListNode(sum);
            //将新链表的节点后移
            cur = cur.next;
            //当链表l1不等于null的时候，将l1 的节点后移
            if(l1 !=null){
                l1 = l1.next;
            }
            //当链表l2 不等于null的时候，将l2的节点后移
            if(l2 !=null){
                l2 = l2.next;
            }
        }
        //如果最后两个数，相加的时候有进位数的时候，就将进位数，赋予链表的新节点。
        //两数相加最多小于20，所以的的值最大只能时1
        if(carry == 1){
            cur.next = new ListNode(carry);
        }
        //返回链表的头节点
        return prev.next;
    }
}
