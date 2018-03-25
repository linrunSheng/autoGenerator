package com.lhy.commonweb.web;


import com.lhy.commonweb.model.Page;
import com.lhy.commonweb.model.RequestPage;
import com.lhy.commonweb.model.ResponseResult;
import com.lhy.commonweb.model.ResponseValidate;
import com.lhy.commonweb.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Set;

/**
 * @author hyluan
 * @ClassName: BaseControllerImpl 注意阀盖方法时入参上的注解不能继承，需要重新设置
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2018/3/14 15:18
 * @Copyright: Copyright (c) 2018 wisedu
 */
public abstract class BaseControllerImpl<T extends Serializable, P extends Serializable>
        implements BaseController<T, P> {

    protected AbstractService<T, P> service;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    public BaseControllerImpl(AbstractService service) {
        this.service = service;
    }

    protected <S> S getService() {
        return (S) this.service;
    }

    /**
     * @param requestPage
     * @param bean
     * @return cn.backflow.admin.common.pagination.Page<T>
     * @throws
     * @Title: query
     * @Description: (这里用一句话描述这个方法的作用)
     * @date 2018/3/14 16:56
     */
    @Override
    public Page<T> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute T bean) {
        return service.queryExample(requestPage.getPage(), requestPage.getRows(), requestPage.getSc(), bean);
    }

    protected void wrapRequestPage(RequestPage requestPage, String configSortColumns) {
        if (RequestPage.DEFAULT_SORT_COLUMNS.equals(requestPage.getSc()) && StringUtils.hasText(configSortColumns)) {
            requestPage.setSc(configSortColumns);
        }
    }

    /**
     * @param
     * @return
     * @throws
     * @Title:
     * @Description: 根据id查询一条记录
     * @date 2018/3/14 15:18
     */
    @Override
    public T get(@PathVariable("id") P id) {
        return service.get(id);
    }

    /**
     * @param bean
     * @return ResponseResult
     * @throws
     * @Title: create
     * @Description: 保存新增
     * @date 2018/3/14 15:23
     */
    @Override
    public ResponseResult<T> create(@RequestBody T bean) {
        return ResponseResult.create(service.add(bean));
    }


    /**
     * @param bean
     * @return ResponseResult
     * @throws
     * @Title: update
     * @Description: (这里用一句话描述这个方法的作用)
     * @date 2018/3/14 15:38
     */
    @Override
    public ResponseResult<T> update(@RequestBody T bean) {
        return ResponseResult.create(service.update(bean));
    }

    /**
     * @param id,
     * @return ResponseResult<T>
     * @throws
     * @Title: delete
     * @Description: 删除
     * @date 2018/3/14 15:39
     */
    @Override
    public ResponseResult<T> delete(@PathVariable("id") P id) {
        return ResponseResult.create(service.delete(id));
    }


    /**
     * 批量删除
     *
     * @param items
     * @return
     */
    @Override
    public ResponseResult<T> delete(@RequestParam("items") Set<P> items) {
        return ResponseResult.create(service.delete(items));
    }

    /**
     * 校验是否存在
     *
     * @param bean
     * @return
     */
    @Override
    public ResponseValidate validate(T bean) {
        return ResponseValidate.create(!service.existsBean(bean));
    }
}
