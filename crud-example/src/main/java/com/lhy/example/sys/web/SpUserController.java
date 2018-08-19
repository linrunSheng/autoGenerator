package com.lhy.example.sys.web;


import com.lhy.common.web.annotation.SimpleMapping;
import com.lhy.common.web.controller.SimpleCrudControllerSupport;
import com.lhy.example.sys.entity.SpUser;
import com.lhy.example.sys.service.SpUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

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
@SimpleMapping
public class SpUserController extends SimpleCrudControllerSupport<SpUserService, SpUser, Integer> {

    @GetMapping("test")
    public Object test(){
        service.testTransactional();
        List<SpUser> list = service.list(new SpUser().setCreateTime(LocalDateTime.now()));
        return list;
    }
}

