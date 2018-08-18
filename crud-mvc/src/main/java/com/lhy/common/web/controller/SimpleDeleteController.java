package com.lhy.common.web.controller;

import com.lhy.common.web.entity.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;

public interface SimpleDeleteController<T extends Serializable, P extends Serializable> {

    /**
     * 根据主键id删除<br/>
     * 请求路径 /path/{id}<br/>
     * delete 方法<br/>
     *
     * @param id
     * @return com.wisedu.selfservice.cloud.ResponseResult
     * @throws
     * @date 2018/8/17 16:12
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据主键id删除", notes = "根据主键id删除")
    ResponseResult delete(@PathVariable("id") P id);

    /**
     * 批量删除，参数为id数组<br/>
     * 请求路径 /path?items=<br/>
     * delete 方法<br/>
     * 参数注解： @Validated @ModelAttribute
     * @param items
     * @return com.wisedu.selfservice.cloud.ResponseResult
     * @throws
     * @date 2018/8/17 16:13
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除，参数为id数组", notes = "批量删除，参数为id数组")
    ResponseResult delete(@RequestParam("items[]") Set<P> items);

    /**
     * 按条件删除<br/>
     * 请求路径 /path/cond?id=</>e
     * delete 方法<br/>
     *参数注解： @RequestParam
     * @param bean
     * @return com.wisedu.selfservice.cloud.ResponseResult
     * @throws
     * @date 2018/8/17 16:14
     */
    @RequestMapping(value = "cond", method = RequestMethod.DELETE)
    @ApiOperation(value = "按条件删除", notes = "按条件删除")
    ResponseResult deleteCond(@Validated @ModelAttribute T bean);
}
