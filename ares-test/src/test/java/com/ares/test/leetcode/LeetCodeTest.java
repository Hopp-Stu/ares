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
    public void tree(){
        TreeNode treeNode = null;
    }
}
