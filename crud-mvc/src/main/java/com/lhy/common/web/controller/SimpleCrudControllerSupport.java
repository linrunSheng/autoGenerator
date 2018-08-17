package com.lhy.common.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhy.common.web.entity.Page;
import com.lhy.common.web.entity.RequestPage;
import com.lhy.common.web.entity.ResponseResult;
import com.lhy.common.web.entity.ResponseValidate;
import com.lhy.common.web.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hyluan
 * @ClassName: SimpleCrudControllerSupport 注意阀盖方法时入参上的注解不能继承，需要重新设置
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2018/3/14 15:18
 * @Copyright: Copyright (c) 2018 wisedu
 */
public abstract class SimpleCrudControllerSupport<S extends ServiceImpl, T extends Serializable, P extends Serializable>
        implements BaseController<T, P> {

    @Autowired
    protected S service;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Override
    public Page<T> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute T bean) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Map<String, Object> entityColumns = ReflectionUtils.getEntityColumns(bean);
        entityColumns.forEach((name, value) -> {
            if (!ObjectUtils.isEmpty(value)) {
                queryWrapper.or().like(name, value);
            }
        });
        com.baomidou.mybatisplus.extension.plugins.pagination.Page page1 = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(requestPage.getPage().longValue(), requestPage.getRows().longValue());
        IPage<T> page = service.page(page1, queryWrapper);
        return new Page<>(requestPage.getPage(), requestPage.getRows(), (int) page.getTotal(), page.getRecords());
    }

    @Override
    public List<T> queryCond(@Validated @ModelAttribute T bean) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Map<String, Object> entityColumns = ReflectionUtils.getEntityColumns(bean);
        entityColumns.forEach((name, value) -> {
            if (!ObjectUtils.isEmpty(value)) {
                queryWrapper.like(name, value);
            }
        });
        return service.list(queryWrapper);
    }

    @Override
    public T get(@PathVariable("id") P id) {
        return (T) service.getById(id);
    }

    @Override
    public T getCond(@Validated @ModelAttribute T bean) {
        List<T> list = service.list(getEqQueryWrapper(bean));
        return list.isEmpty() ? null : list.get(0);
    }

    private QueryWrapper<T> getEqQueryWrapper(@Validated @ModelAttribute T bean) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Map<String, Object> entityColumns = ReflectionUtils.getEntityColumns(bean);
        entityColumns.forEach((name, value) -> {
            if (!ObjectUtils.isEmpty(value)) {
                queryWrapper.eq(name, value);
            }
        });
        return queryWrapper;
    }


    @Override
    public ResponseResult create(@RequestBody T bean) {
        return ResponseResult.create(service.save(bean));
    }

    @Override
    public ResponseResult update(@RequestBody T bean) {
        return ResponseResult.create(service.updateById(bean));
    }


    @Override
    public ResponseResult delete(@PathVariable("id") P id) {
        return ResponseResult.create(service.removeById(id));
    }

    @Override
    public ResponseResult delete(@RequestParam("items") Set<P> items) {
        return ResponseResult.create(service.removeByIds(items));
    }


    @Override
    public ResponseResult deleteCond(@Validated @ModelAttribute T bean) {
        QueryWrapper<T> eqQueryWrapper = getEqQueryWrapper(bean);
        return ResponseResult.create(service.remove(eqQueryWrapper));
    }

    @Override
    public ResponseValidate validate(T bean) {
        List<T> list = service.list(getEqQueryWrapper(bean));
        return ResponseValidate.create(list.isEmpty());
    }
}
