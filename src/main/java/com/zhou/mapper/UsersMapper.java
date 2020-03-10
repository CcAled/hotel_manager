package com.zhou.mapper;

import com.zhou.pojo.Users;
import com.zhou.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UsersMapper extends MyMapper<Users> {
}