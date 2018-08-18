package com.lhy.common.web.controller;

import com.lhy.common.web.entity.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface SimpleCreateController<T extends Serializable> {

    /**
     * 新增一个对象，参数为请求体json串<br/>
     * 请求路径 /path<br/>
     * post方法<br/>
     * 参数注解： @RequestBody
     * @param bean
     * @throws
     * @return com.wisedu.selfservice.cloud.ResponseResult
     * @date 2018/8/17 16:10
     */
    @ApiOperation(value = "新增一个对象，参数为请求体json串",notes = "新增一个对象，参数为请求体json串")
    @RequestMapping(method = RequestMethod.POST)
    ResponseResult create(@RequestBody T bean);
}