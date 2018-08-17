package com.lhy.common.web.controller;

import com.lhy.common.web.entity.ResponseValidate;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseValidateController<T extends Serializable> {

    /**
     * 校验是否存在<br/>
     * 请求路径 /path/validation?id=<br/>
     * get 方法<br/>
     * 参数注解: @ModelAttribute @Validate
     * @param bean
     * @return com.wisedu.selfservice.cloud.ResponseValidate
     * @throws
     * @date 2018/8/17 16:18
     */
    @ApiOperation(value = "校验是否存在", notes = "校验是否存在，传入字段查询记录存在时返回valid false")
    @RequestMapping(value = "validation", method = RequestMethod.GET)
    ResponseValidate validate(@ModelAttribute @Validated T bean);
}
