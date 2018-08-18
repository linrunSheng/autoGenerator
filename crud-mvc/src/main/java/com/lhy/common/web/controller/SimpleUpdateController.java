package com.lhy.common.web.controller;

import com.lhy.common.web.entity.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface SimpleUpdateController<T extends Serializable> {

    /**
     * 更新对象，根据主键id更新记录，空字段不更新<br/>
     * 请求路径 /path<br/>
     * put 方法<br/>
     * 参数注解: @RequestBody
     * @param bean
     * @return com.wisedu.selfservice.cloud.ResponseResult
     * @throws
     * @date 2018/8/17 16:18
     */
    @ApiOperation(value = "更新对象，根据主键id更新记录，空字段不更新", notes = "更新对象，根据主键id更新记录，空字段不更新")
    @RequestMapping(method = RequestMethod.PUT)
    ResponseResult update(@RequestBody T bean);
}
