package com.lhy.commonweb.web;

import com.lhy.commonweb.model.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseCreateController<T extends Serializable> {

    /**
     * @param bean
     * @return ResponseResult
     * @throws
     * @Title: create
     * @Description: 保存新增
     * @date 2018/3/14 15:23
     */
    @ApiOperation(value = "新增",notes = "新增一个对象，参数为请求体json串")
    @RequestMapping(method = RequestMethod.POST)
    ResponseResult<T> create(@RequestBody T bean);
}