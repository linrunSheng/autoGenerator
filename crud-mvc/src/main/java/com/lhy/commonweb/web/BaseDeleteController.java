package com.lhy.commonweb.web;

import com.lhy.commonweb.model.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Set;

public interface BaseDeleteController<T extends Serializable, P extends Serializable> {

    /**
     *  删除
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = " 删除",notes = "根据主键id删除")
    ResponseResult<T> delete(@PathVariable("id") P id);

    /**
     *  批量删除
     * @param items
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除",notes = "批量删除，参数为id数组")
    ResponseResult<T> delete(@RequestParam("items") Set<P> items);
}
