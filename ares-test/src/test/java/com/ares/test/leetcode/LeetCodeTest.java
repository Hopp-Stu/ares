/*******************************************************************************
 * Copyright (c) 2021 - 9999, ARES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.ares.test.leetcode;

import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Young
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
