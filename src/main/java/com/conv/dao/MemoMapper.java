package com.conv.dao;

import com.conv.model.Memo;

public interface MemoMapper {
    int deleteByPrimaryKey(Integer memoId);

    int insert(Memo record);

    int insertSelective(Memo record);

    Memo selectByPrimaryKey(Integer memoId);

    int updateByPrimaryKeySelective(Memo record);

    int updateByPrimaryKey(Memo record);
}