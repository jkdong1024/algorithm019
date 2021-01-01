package com.geektime.lesson;

import java.util.*;

/**
 * 类描述:     [Week_02]
 * 创建人:     [jkdong]
 * 创建时间:   [2020/11/23 20:47]
 * 版本:       [v1.0]
 */
public class Lesson05Hash {

    public static void main(String[] args) {

    }

    //  异位词判断
    //  题目; 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    //  名词解释：异位词：位置不同的词汇  此处大小写忽略
    public boolean isAnagram(String s, String t) {

        //  思路一： 先排序然后对比  异位词等价于顺序排序后字符串内容一致
        /*if(s.length() != t.length()){
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1,str2);*/

        //  思路二： 两个字符串中字符种类和s数量相等
        if(s.length() != t.length()){
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            //  s使得table各个元素+1
            table[s.charAt(i) - 'a']++;
            //  t使得table各个元素-1
            table[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < table.length; i++) {
            //  table元素不为0时 说明s和t存在不同字母
            if (table[i] != 0){
                return false;
            }
        }
        return true;

        //  return true;
    }

    //  字母异位词分组
    //  题目：给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
    //  说明：
    //  所有输入均为小写字母。
    //  不考虑答案输出的顺序。
    /*示例;
    输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
    输出:
            [
            ["ate","eat","tea"],
            ["nat","tan"],
            ["bat"]
            ]*/
    public List<List<String>> groupAnagrams(String[] strs) {
        //  思路1：排序数组分类
        /*if (strs.length == 0){
            return new ArrayList<List<String>>();
        }
        Map<String,List> ans = new HashMap<String, List>();
        for (String str : strs) {
            //  将字符串转为字符数组并排序
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            //  排序后的字符数组转为字符串 作为key str作为value
            String k = String.valueOf(ca);
            if(!ans.containsKey(k)){
                ans.put(k, new ArrayList());
            }
            ans.get(k).add(str);
        }
        return  new ArrayList(ans.values());*/

        //  思路2 同上
        HashMap<String, List<String>> hash = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            char[] s_arr = strs[i].toCharArray();
            //排序
            Arrays.sort(s_arr);
            //映射到 key
            String key = String.valueOf(s_arr);
            //添加到对应的类中
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());

        //  return null;
    }

    //  两数之和
    //  题目：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    public int[] twoSum(int[] nums, int target) {
        //  思路1：暴力枚举 略

        //  思路2：哈希表  同时判断x和targe-x是否在数组中   遍历的同时缓存信息  空间换时间
        /*Map<Integer,Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(target - nums[i])){
                return new int[]{hashtable.get(target - nums[i]),i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];*/
        int len = nums.length;
        //  创建hash  以数组元素为key  以数组index为hash的value  hash容量为len-1  遍历到最后一个元素之前肯定就已经知道结果
        Map<Integer,Integer> hashMap = new HashMap<Integer, Integer>(len -1);
        // 将第一个元素初始化到hash
        hashMap.put(nums[0], 0);
        for (int i = 0; i < len; i++) {
            //  先检查target-x是否在hash中 如果在 返回两个inde
            if(hashMap.containsKey(target - nums[i])){
                return new int[]{i,hashMap.get(target - nums[i])};
            }else {
                //  target-x不在hash中 将数据元素和index放入hash
                hashMap.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");

        //return null;
    }


}
