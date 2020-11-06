package com.ares.core.service;

import com.ares.core.dao.ISysNoticeReadDao;
import com.ares.core.model.SysNoticeRead;
import com.ares.core.utils.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysNoticeReadService implements BaseService<SysNoticeRead>{

    @Resource
    private ISysNoticeReadDao sysNoticeReadDao;

    public PageInfo<SysNoticeRead> list(int pageNo, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysNoticeRead> lists = sysNoticeReadDao.list(map);
        PageInfo<SysNoticeRead> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public void insert(SysNoticeRead obj) {
        obj.setId(SnowflakeIdWorker.getUUID());
        obj.setCreateTime(new Date());
        sysNoticeReadDao.insert(obj);
    }

    @Override
    public void update(SysNoticeRead obj) {
        obj.setModifyTime(new Date());
        sysNoticeReadDao.update(obj);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        sysNoticeReadDao.deleteByIds(ids);
    }

    @Override
    public SysNoticeRead getById(String id) {
        return sysNoticeReadDao.getById(id);
    }

    public List<SysNoticeRead> list(SysNoticeRead obj) {
        List<SysNoticeRead> lists = sysNoticeReadDao.selectList(obj);
        return lists;
    }

}