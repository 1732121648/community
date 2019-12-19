package com.majiang.commounity.mapper;

import com.majiang.commounity.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMaper {

    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}



