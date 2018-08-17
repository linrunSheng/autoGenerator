package com.lhy.example.sys.web;


import com.lhy.common.web.annotation.SimpleMapping;
import com.lhy.common.web.controller.SimpleCrudControllerSupport;
import com.lhy.common.web.entity.Page;
import com.lhy.common.web.entity.RequestPage;
import com.lhy.example.sys.entity.SpUser;
import com.lhy.example.sys.service.SpUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author hyluan
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/sys/sp-user")
public class SpUserController extends SimpleCrudControllerSupport<SpUserService, SpUser, Integer> {

    @Override
    @SimpleMapping
    public SpUser get(@PathVariable("id") Integer id) {
        return super.get(id);
    }

    @Override
    @SimpleMapping
    public Page<SpUser> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute SpUser bean) {
        return super.query(requestPage, bean);

    }

    @GetMapping("test/test")
    public Object test(){
       return "123";
    }
}

