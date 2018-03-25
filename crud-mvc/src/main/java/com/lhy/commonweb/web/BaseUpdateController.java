package com.lhy.commonweb.web;

import com.lhy.commonweb.model.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseUpdateController<T extends Serializable> {

    /**
     * @param bean
     * @return ResponseResult
     * @throws
     * @Title: update
     * @Description: 更新对象
     * @date 2018/3/14 15:38
     */
    @ApiOperation(value = "更新对象",notes = "更新对象，根据主键id更新记录，空字段不更新")
    @RequestMapping(method = RequestMethod.PUT)
    ResponseResult<T> update(@RequestBody T bean);
}
