package com.lhy.common.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseGetController<T extends Serializable, P extends Serializable> {


    /**
     * 根据id查询一条记录<br/>
     * 请求路径 /path/{id}<br/>
     * get 方法<br/>
     * 参数注解： @PathVariable("id")
     * @param id
     * @throws
     * @return T
     * @date 2018/8/17 16:03
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询一条记录",notes = "根据id查询一条记录")
    T get(@PathVariable("id") P id);

    /**
     * 根据条件查询一条记录<br/>
     * 请求路径 /path/one<br/>
     * get方法<br/>
     * 参数注解：@Validated @ModelAttribute
     * @param bean
     * @throws
     * @return T
     * @date 2018/8/17 16:07
     */
    @RequestMapping(value = "one", method = RequestMethod.GET)
    @ApiOperation(value = "根据多个条件查询一条记录",notes = "根据条件查询一条记录")
    T getCond(@Validated @ModelAttribute T bean);
}
