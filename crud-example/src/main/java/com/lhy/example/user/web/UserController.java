package com.lhy.example.user.web;

import com.lhy.commonweb.model.Page;
import com.lhy.commonweb.model.RequestPage;
import com.lhy.commonweb.service.AbstractService;
import com.lhy.commonweb.web.BaseControllerImpl;
import com.lhy.example.user.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
* @ClassName: UserController
* @Description: User（）
* @author hyluan
* @date 2017年9月29日 上午11:03:30
* @Copyright: Copyright (c) 2017 wisedu
 */
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseControllerImpl<User, java.lang.Integer>{

	public UserController(@Qualifier("userService") AbstractService service) {
		super(service);
	}

	@Override
	public Page<User> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute User bean) {
		super.wrapRequestPage(requestPage, "updated desc");
		return super.query(requestPage, bean);
	}
 
}
