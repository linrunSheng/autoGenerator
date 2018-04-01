package com.lhy.commonweb.web;

import com.lhy.commonweb.model.Page;
import com.lhy.commonweb.model.RequestPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

public interface BaseQueryController<T extends Serializable> {

    /**
     * @param requestPage
     * @param bean
     * @return Page<T>
     * @throws
     * @Title: query
     * @Description: 分页查询
     * @date 2018/3/14 16:56
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页查询，传入分页条件，排序字段，查询字段",notes = "分页查询，传入分页条件，排序字段，查询字段")
    Page<T> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute T bean);

    /**
     * @param bean
     * @return List<T>
     * @throws
     * @Title: queryCond
     * @Description: 查询
     * @date 2018/3/14 16:56
     */
    @RequestMapping(value="list",method = RequestMethod.GET)
    @ApiOperation(value = "按多个条件查询",notes = "按多个条件查询")
    List<T> queryCond(@Validated @ModelAttribute T bean);
}
