package com.ares.test.leetcode;

import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/23
 * @see: com.ares.test.leetcode LeetCodeTest.java
 **/

public class LeetCodeTest {

    @Test
    public void twoSum() {
        int[] nums = new int[]{21, 72, 2, 7};
        int target = 9;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int rs = target - nums[i];
            if (map.containsKey(rs)) {
                System.out.println("num1:" + map.get(rs) + ",num2:" + i);
                break;
            }
            map.put(nums[i], i);
        }
    }

    @Test
    public void tree() {
        TreeNode treeNode = null;
    }

    @Test
    public void findLuckyNum() {
        int[] nums = new int[]{2, 2, 3, 1, 2, 3, 4, 3};
        int result = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.get(n) == null ? 1 : map.get(n) + 1);
        }
        for (Integer key : map.keySet()) {
            if (key == map.get(key)) {
                result = Math.max(result, key);
            }
        }
        System.out.println("the lucky number is:" + result);
    }
}
