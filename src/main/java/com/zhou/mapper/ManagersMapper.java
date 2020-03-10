package com.zhou.mapper;

import com.zhou.pojo.Managers;
import com.zhou.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ManagersMapper extends MyMapper<Managers> {
}