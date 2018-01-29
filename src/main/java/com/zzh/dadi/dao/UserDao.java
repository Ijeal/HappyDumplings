package com.zzh.dadi.dao;

import com.github.abel533.mapper.Mapper;
import com.zzh.dadi.po.User;

public interface UserDao extends Mapper<User> {
/*	  @Select("select * from user;")
	  public List<User> getAllUsers();
	  @Select("select * from user where id=#{userId};")
	  public User getUser(String userId);
	  @Select("select * from user where id=#{userId};")
	  public User getUsersByPage(String userId);*/
}
