package com.ares.core.service;

import com.ares.core.dao.ISysNoticeDao;
import com.ares.core.dao.ISysNoticeReadDao;
import com.ares.core.model.SysNotice;
import com.ares.core.model.SysNoticeRead;
import com.ares.core.utils.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysNoticeService implements BaseService<SysNotice> {

    @Resource
    ISysNoticeDao sysNoticeDao;
    @Resource
    private ISysNoticeReadDao noticeReadDao;

    public PageInfo<SysNotice> list(int pageNo, int pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysNotice> lists = sysNoticeDao.list(map);
        PageInfo<SysNotice> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }

    @Override
    public void insert(SysNotice obj) {
        obj.setId(SnowflakeIdWorker.getUUID());
        obj.setCreateTime(new Date());
        sysNoticeDao.insert(obj);
    }

    @Override
    public void update(SysNotice obj) {
        obj.setModifyTime(new Date());
        sysNoticeDao.update(obj);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        sysNoticeDao.deleteByIds(ids);
    }

    @Override
    public SysNotice getById(String id) {
        return sysNoticeDao.getById(id);
    }

    public List<SysNotice> list(SysNotice obj) {
        List<SysNotice> lists = sysNoticeDao.selectList(obj);
        return lists;
    }

    public int noticeNum(String userId) {
        return sysNoticeDao.noticeNum(userId);
    }

    public List<SysNotice> getNotices(String userId) {
        List<SysNotice> noticeList = sysNoticeDao.getNotices();
        List<SysNoticeRead> noticeReads = new ArrayList<>();
        if (null != noticeList && noticeList.size() > 0) {
            List<String> noticeReadList = noticeReadDao.getByUser(userId);
            for (SysNotice sysNotice : noticeList) {
                if (noticeReadList.contains(sysNotice.getId())) {
                    continue;
                }
                SysNoticeRead sysNoticeRead = new SysNoticeRead();
                sysNoticeRead.setId(SnowflakeIdWorker.getUUID());
                sysNoticeRead.setNoticeId(sysNotice.getId());
                sysNoticeRead.setUserId(userId);
                sysNoticeRead.setCreator(userId);
                sysNoticeRead.setCreateTime(new Date());
                noticeReads.add(sysNoticeRead);
            }
        }
        if (null != noticeReads && noticeReads.size() > 0) {
            noticeReadDao.batchInsert(noticeReads);
        }
        return noticeList;
    }

}