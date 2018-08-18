package com.lhy.common.web.service;

import com.lhy.common.web.entity.Page;
import com.lhy.common.web.entity.SimplePage;

import java.util.List;
import java.util.Optional;

/**
 * 扩展service接口
 * @param <T>
 */
public interface ExtendService<T> {

    /**
     * 按等于条件获取一个对象 空字段忽略
     * @param bean
     * @return
     */
    Optional<T> getOne(T bean);

    /**
     * 按等于条件获取列表对象 空字段忽略
     * @param bean
     * @return
     */
    List<T> list(T bean);

    /**
     * 按等于条件删除对象 空字段忽略
     * @param bean
     * @return
     */
    boolean remove(T bean);

    /**
     * 按等于条件判断对象是否存在 空字段忽略
     * @param bean
     * @return
     */
    boolean exist(T bean);

    /**
     * 按like条件查询分页对象， 空字段忽略
     * @param simplePage
     * @param bean
     * @return
     */
    Page<T> paging(SimplePage simplePage, T bean);
}
