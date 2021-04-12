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

package com.ares.core.persistence.service;

import com.ares.core.persistence.dao.ISysDeptDao;
import com.ares.core.persistence.model.system.SysDept;
import com.ares.core.persistence.model.tree.TreeSelect;
import com.ares.core.utils.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SysDeptService implements BaseService<SysDept> {

    @Resource
    ISysDeptDao sysDeptDao;

    public PageInfo<SysDept> list(int pageNo, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysDept> lists = sysDeptDao.list(map);
        PageInfo<SysDept> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public void insert(SysDept obj) {
        obj.setId(SnowflakeIdWorker.getUUID());
        obj.setCreateTime(new Date());
        sysDeptDao.insert(obj);
    }

    @Override
    public void update(SysDept obj) {
        obj.setModifyTime(new Date());
        sysDeptDao.update(obj);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        sysDeptDao.deleteByIds(ids);
    }

    @Override
    public SysDept getById(String id) {
        return sysDeptDao.getById(id);
    }

    public List<SysDept> list(SysDept obj) {
        List<SysDept> lists = sysDeptDao.selectList(obj);
        return lists;
    }

    public SysDept getByDeptId(String id) {
        return sysDeptDao.getByDeptId(id);
    }

    public List<TreeSelect> buildDeptTree() {
        List<SysDept> deptList = sysDeptDao.getAllDept();
        return buildTree("0", deptList);
    }

    public List<TreeSelect> buildTree(String parentId, List<SysDept> deptList) {
        List<TreeSelect> trees = new LinkedList<>();
        for (SysDept sysDept : deptList) {
            TreeSelect tree = new TreeSelect();
            if (parentId.equals(sysDept.getParentDeptId())) {
                tree.setId(sysDept.getId());
                tree.setLabel(sysDept.getDeptName());
                if (sysDept.getChildCount() > 0) {
                    tree.setChildren(buildTree(sysDept.getId(), deptList));
                }
                trees.add(tree);
            }
        }
        return trees;
    }

}