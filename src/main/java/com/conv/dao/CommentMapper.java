package com.conv.dao;

import com.conv.model.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}