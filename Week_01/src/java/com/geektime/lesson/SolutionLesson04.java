package com.geektime.lesson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述:     [Lesson4 ]
 * 创建人:     [jkdong]
 * 创建时间:   [2020/11/08 10:10:22]
 * 版本:       [v1.0]
 */

class SolutionLesson04 {

    public static void main(String[] args) {
        //  移动零
        //  int nums[] = {0,1,0,3,12};
        //  int[] result = moveZeroes(nums);
        //  System.out.println("执行结果为>>>>" + Arrays.toString(result));  //    执行结果为>>>>[1, 3, 12, 0, 0]
        int nums[] = {1,8,6,2,5,4,8,3,7};
        int result = maxArea(nums);
        System.out.println("执行结果为>>>>" + result);  //   执行结果为>>>>49
    }

    //  移动零
    //  题目：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    public static int[] moveZeroes(int[] nums) {
        if(null == nums || nums.length <= 0){
            return null;
        }
        int j = 0;
        //  第一次遍历取出所有非零元素 赋值给前j项
        for(int i = 0; i < nums.length; i++ ){
            if(nums[i] != 0){
                nums[j] = nums[i];
                j++;
            }
        }
        //  将j之后的赋值为0
        for(int i = j; i < nums.length; i++){
            nums[i] = 0;
        }
        return nums;
    }

    //  盛水最多求解
    //  题目：给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。说明：你不能倾斜容器。
    public static int maxArea(int[] height){

        //  暴力解法
        //  时空复杂度：O(n^2) 和 O(1)
        /*int res = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                res = Math.max(res, (j - i) * Math.min(height[i],height[j]));
            }
        }
        return  res;*/
        //  ===================================
        //  两侧收敛  双指针法
        //  时空复杂度：O(n)  O(1)
        int leftIdx = 0, rightIdx = height.length - 1, res = 0;
        while(leftIdx < rightIdx){
            //  面积受限于短边
            //  向中间夹逼的时候如果左边比右边小移动左边然后与上次结果比较，反之移动右边进行比较 -- 每选中一次左右边，先固定长边，另一短边向内移动判断面积变化
            res = height[leftIdx] < height[rightIdx] ?
                Math.max(res,(rightIdx - leftIdx) * height[leftIdx++]) :
                Math.max(res,(rightIdx - leftIdx) * height[rightIdx--]);
        }
        return res;
    }

    //  爬楼梯
    //  题目：假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？注意：给定 n 是一个正整数。
    //  第n级只有两种可能：一种是在n-1级跨1级，另一种是在n-1上跨2级  ====>  爬楼梯转化为求斐波那契数列问题 f(n) = f(n - 1) + f(n - 2)
    public int climbStairs(int n) {
        //  方法1：递归
        //  时空复杂度：O(2^n) O(n)
        /*if(1 == n){
            return 1;
        }else if(2 == n){
            return  2;
        }else {
            return climbStairs(n-1) + climbStairs(n - 2);
        }*/

        //  ======================================================================
        //  方法2：记忆化递归
        //  时空复杂度 O(n) O(n)
        /*int mem[] = new int[n + 1];
        return climbStairsMem(n, mem);*/

        //  ======================================================================
        //  方法3：动态规划实现 使用缓存
        //  时空复杂度 O(n) O(1)
        int a = 0, b = 0, c = 1;
        for (int i = 1; i < n + 1 ; i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }

    private int climbStairsMem(int n, int[] mem) {
        if (mem[n] > 0){
            return mem[n];
        }
        if(1 == n){
            mem[n] = 1;
        }else if(2 == n){
            mem[n] = 2;
        }else {
            mem[n] = climbStairsMem(n - 1, mem) + climbStairsMem(n - 2, mem);
        }
        return mem[n];
    }


    //  两数之和
    //  题目：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    public int[] twoSum(int[] nums, int target) {
        //  暴力求解 嵌套循环
        //  时空复杂度 O(n^2) O(1)
        /*for (int i = 0; i < nums.length - 1 ; i++) {
            for (int j = i + 1; j < nums.length ; j++) {
                if(target == nums[i] + nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        throw  new IllegalArgumentException("没有这样的元素存在");*/

        //  =====================================================
        //  哈希表
        //  将数组中已经遍历过得元素放入到哈希表中，依次判断target - x 是否在哈希表中
        int len = nums.length;
        //  源数组中剩余一个元素时一定是能够完成比较
        Map<Integer,Integer> hashMap = new HashMap<>(len - 1);
        //  初始放入第一个数组元素  以数组元素为key以数组index为value
        hashMap.put(nums[0], 0);
        for (int i = 1; i < len ; i++) {
            int another = target - nums[i];
            if (hashMap.containsKey(another)){
                return new int[]{i, hashMap.get(another)};
            }else {
                hashMap.put(nums[i], i);
            }
        }
        throw  new IllegalArgumentException("没有这样的元素存在");
    }


    //  三数之和
    //  题目：给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。注意：答案中不可以包含重复的三元组。
    public List<List<Integer>> threeSum(int[] nums) {
        //  暴力解法  三重循环

        return null;
    }
}