package com.conv.service.impl;

import com.conv.dao.MemoMapper;
import com.conv.model.Memo;
import com.conv.service.MemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MemoServiceImp implements MemoService {

    @Resource
    MemoMapper memoMapper;

    @Override
    public int deleteByPrimaryKey(Integer memoId) {
        return memoMapper.deleteByPrimaryKey(memoId);
    }

    @Override
    public int insert(Memo record) {
        record.setEditTime(time());
        record.setState(0);
        int memoId = memoMapper.insert(record);
        return memoId;
    }

    @Override
    public int insertSelective(Memo record) {
        return 0;
    }

    @Override
    public Memo selectByPrimaryKey(Integer memoId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Memo record) {
        record.setEditTime(time());
        record.setState(0);
        return memoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Memo record) {
        return memoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Memo> selectByUserId(int userId) {
        return memoMapper.selectByUserId(userId);
    }

    public Timestamp time()
    {
        Timestamp t1=null ;
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        try {
            Date date1 = format.parse(time);
            System.out.println(date1);
            t1 = new Timestamp(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t1;
    }
}
