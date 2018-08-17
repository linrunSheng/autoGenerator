package com.lhy.common.web.controller;

import com.lhy.common.web.entity.Page;
import com.lhy.common.web.entity.RequestPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

public interface BaseQueryController<T extends Serializable> {
    /**
     * 分页查询，传入分页条件，排序字段，查询字段<br/>
     * 请求路径 /path?id=<br/>
     * get方法<br/>
     * 参数注解:  @Validated @ModelAttribute
     * @param requestPage
     * @param bean
     * @return com.wisedu.selfservice.cloud.Page<T>
     * @throws
     * @date 2018/8/17 16:16
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页查询，传入分页条件，排序字段，查询字段", notes = "分页查询，传入分页条件，排序字段，查询字段")
    Page<T> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute T bean);

    /**
     * 按多个条件查询<br/>
     * 请求路径 /path/list?id=<br/>
     * get方法<br/>
     * 参数注解: @Validated @ModelAttribute
     * @param bean
     * @return java.util.List<T>
     * @throws
     * @date 2018/8/17 16:17
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ApiOperation(value = "按多个条件查询", notes = "按多个条件查询")
    List<T> queryCond(@Validated @ModelAttribute T bean);
}
