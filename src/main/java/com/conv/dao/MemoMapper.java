package com.conv.dao;

import com.conv.model.Memo;

import java.util.List;

public interface MemoMapper {
    int deleteByPrimaryKey(Integer memoId);

    int insert(Memo record);

    int insertSelective(Memo record);

    Memo selectByPrimaryKey(Integer memoId);

    int updateByPrimaryKeySelective(Memo record);

    int updateByPrimaryKey(Memo record);

    List<Memo> selectByUserId(int userId);
}