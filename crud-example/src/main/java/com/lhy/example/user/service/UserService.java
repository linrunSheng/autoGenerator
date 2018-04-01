package com.lhy.example.user.service;

import com.lhy.commonweb.service.AbstractService;
import com.lhy.example.user.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;


/**
 * 
* @ClassName: UserService
* @Description: User（）
* @author
* @date 2017年12月18日 下午5:08:53
* @Copyright: Copyright (c) 2017 wisedu
 */
@Service
public class UserService extends AbstractService<User, java.lang.Integer>{

	public UserService(@Qualifier("userMapper") Mapper<User> mapper) {
		super(mapper);
	}

}
