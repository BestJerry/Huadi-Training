package com.hd.logOperationMapper;

import java.util.List;

import com.hd.beans.Log;

public interface LogMapper {

    int deleteByPrimaryKey(Integer log_id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer log_id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
    
    List<Log> selectByAccount(Integer accountId);
}