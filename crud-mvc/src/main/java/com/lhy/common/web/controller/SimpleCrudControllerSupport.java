package com.lhy.common.web.controller;


import com.lhy.common.web.entity.Page;
import com.lhy.common.web.entity.ResponseResult;
import com.lhy.common.web.entity.ResponseValidate;
import com.lhy.common.web.entity.SimplePage;
import com.lhy.common.web.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * SimpleCrudControllerSupport<br/>
 * 简易controller支持类，提供了常用的增删改查方法<br/>
 * 注意覆盖方法时，入参上的注解不能继承，需要重新设置
 * @author hyluan
 */
public abstract class SimpleCrudControllerSupport<S extends SimpleService, T extends Serializable, P extends Serializable>
        implements SimpleCrudController<T, P> {

    @Autowired
    protected S service;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Override
    public Page<T> query(@Validated @ModelAttribute SimplePage simplePage, @Validated @ModelAttribute T bean) {
        return service.paging(simplePage, bean);
    }

    @Override
    public List<T> queryCond(@Validated @ModelAttribute T bean) {
        return service.list(bean);
    }

    @Override
    public T get(@PathVariable("id") P id) {
        return (T) service.getById(id);
    }

    @Override
    public T getCond(@Validated @ModelAttribute T bean) {
        Optional<T> one = service.getOne(bean);
        return one.orElse(null);
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
        return ResponseResult.create(service.remove(bean));
    }

    @Override
    public ResponseValidate validate(T bean) {
        return ResponseValidate.create(service.exist(bean));
    }
}
