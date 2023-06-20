package com.whq.dao;

import com.whq.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 罗长久
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> findAll();

    //按姓名模糊查找
    @Select("SELECT * FROM users where name like concat('%',#{keywords},'%')")
    List<User> findByName(String keywords);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") Long id);

    @Insert("INSERT INTO users( name, email) VALUES ( #{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(@Param("id") Long id);
}
