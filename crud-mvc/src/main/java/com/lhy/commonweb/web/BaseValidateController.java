package com.lhy.commonweb.web;

import com.lhy.commonweb.model.ResponseValidate;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseValidateController<T extends Serializable> {

    /**
     *  校验是否存在
     * @param bean
     * @return
     */
    @ApiOperation(value = "校验是否存在",notes = "校验是否存在，传入字段查询记录存在时返回valid false")
    @RequestMapping(value = "validation", method = RequestMethod.GET)
    ResponseValidate validate(T bean);
}
