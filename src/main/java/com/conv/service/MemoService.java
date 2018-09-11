package com.conv.service;

import com.conv.model.Memo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemoService {

    int deleteByPrimaryKey(Integer memoId);

    int insert(Memo record);

    int insertSelective(Memo record);

    Memo selectByPrimaryKey(Integer memoId);

    int updateByPrimaryKeySelective(Memo record);

    int updateByPrimaryKey(Memo record);

    List<Memo> selectByUserId(int userId);
}
