package com.lhy.commonweb.web;

import com.lhy.commonweb.model.Page;
import com.lhy.commonweb.model.RequestPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseQueryController<T extends Serializable> {

    /**
     * @param requestPage
     * @param bean
     * @return cn.backflow.admin.common.pagination.Page<T>
     * @throws
     * @Title: query
     * @Description: 分页查询
     * @date 2018/3/14 16:56
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页查询",notes = "分页查询，传入分页条件，排序字段，查询字段")
    Page<T> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute T bean);
}
