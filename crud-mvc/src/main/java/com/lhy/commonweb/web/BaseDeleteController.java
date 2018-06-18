package com.lhy.commonweb.web;

import com.lhy.commonweb.model.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;

public interface BaseDeleteController<T extends Serializable, P extends Serializable> {

    /**
     *  删除
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据主键id删除",notes = "根据主键id删除")
    ResponseResult delete(@PathVariable("id") P id);

    /**
     *  批量删除
     * @param items
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除，参数为id数组",notes = "批量删除，参数为id数组")
    ResponseResult delete(@RequestParam("items[]") Set<P> items);

    /**
     *  按条件删除
     * @param bean
     * @return
     */
    @RequestMapping(value = "cond", method = RequestMethod.DELETE)
    @ApiOperation(value = "按条件删除",notes = "按条件删除")
    ResponseResult deleteCond(@Validated @ModelAttribute T bean);
}
