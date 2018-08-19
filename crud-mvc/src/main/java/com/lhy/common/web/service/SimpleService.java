package com.lhy.common.web.service;


import com.baomidou.mybatisplus.core.conditions.interfaces.Compare;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhy.common.web.entity.Page;
import com.lhy.common.web.entity.SimplePage;
import com.lhy.common.web.util.ReflectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 简易增删改查服务，在mybatis-plus service基础上扩展了一些常用方法
 *
 * @param <M>
 * @param <T>
 */
public abstract class SimpleService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements ExtendService<T> {

    /**
     * 构建QueryWrapper
     *
     * @param bean             实体对象
     * @param condition        实体列处理条件，
     * @param ignoreNullColumn
     * @return
     */
    protected QueryWrapper<T> newQueryWrapper(T bean, EntityColumnCondition<T> condition, boolean ignoreNullColumn) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Map<String, Object> entityColumns = ReflectionUtils.getEntityColumns(bean);
        for (Map.Entry<String, Object> entity : entityColumns.entrySet()) {
            if (ignoreNullColumn) {
                if (!ObjectUtils.isEmpty(entity.getValue())) {
                    queryWrapper = queryWrapper(queryWrapper, condition, entity);
                }
            } else {
                queryWrapper = queryWrapper(queryWrapper, condition, entity);
            }
        }
        return queryWrapper;
    }

    private QueryWrapper<T> queryWrapper(QueryWrapper<T> queryWrapper, EntityColumnCondition<T> condition, Map.Entry<String, Object> entity) {
        return condition.operate(queryWrapper, entity.getKey(), entity.getValue());
    }

    /**
     * 构建QueryWrapper 所有非空字段参与
     *
     * @param bean
     * @param condition
     * @return
     */
    private QueryWrapper<T> newIgnoreNullColumnQueryWrapper(T bean, EntityColumnCondition<T> condition) {
        return newQueryWrapper(bean, condition, true);
    }

    /**
     * 构建QueryWrapper 所有字段参与，包含空字段
     *
     * @param bean
     * @param condition
     * @return
     */
    private QueryWrapper<T> newNoIgnoreNullColumnQueryWrapper(T bean, EntityColumnCondition<T> condition) {
        return newQueryWrapper(bean, condition, false);
    }

    private QueryWrapper<T> newAndEqQueryWrapper(T bean) {
        return newIgnoreNullColumnQueryWrapper(bean, Compare::eq);
    }

    private QueryWrapper<T> newOrEqQueryWrapper(T bean) {
        return newIgnoreNullColumnQueryWrapper(bean, (w, k, v) -> w.or().eq(k, v));
    }

    private QueryWrapper<T> newOrLikeQueryWrapper(T bean) {
        return newIgnoreNullColumnQueryWrapper(bean, (w, k, v) -> {
            if (isEqColumn(v)) {
                w.eq(k, v);
            } else {
                w.or().like(k, v);
            }
            return w;
        });
    }

    private boolean isEqColumn(Object v) {
        return LocalDateTime.class.isAssignableFrom(v.getClass()) || Date.class.isAssignableFrom(v.getClass());
    }

    private QueryWrapper<T> newAndLikeQueryWrapper(T bean) {
        return newIgnoreNullColumnQueryWrapper(bean, (w, k, v) -> {
            if (isEqColumn(v)) {
                w.eq(k, v);
            } else {
                w.like(k, v);
            }
            return w;
        });
    }

    @Override
    public Optional<T> getOne(T bean) {
        return this.list(newAndEqQueryWrapper(bean)).stream().findFirst();
    }

    @Override
    public List<T> list(T bean) {
        return this.list(newAndEqQueryWrapper(bean));
    }

    @Override
    public boolean remove(T bean) {
        return this.remove(newAndEqQueryWrapper(bean));
    }

    @Override
    public boolean exist(T bean) {
        return !this.list(newAndEqQueryWrapper(bean)).isEmpty();
    }

    @Override
    public Page<T> paging(SimplePage simplePage, T bean) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page1 = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(simplePage.getPage().longValue(), simplePage.getRows().longValue());
        QueryWrapper<T> queryWrapper = newOrLikeQueryWrapper(bean);
        simplePage.getSort().forEach(sort -> queryWrapper.orderBy(true, SqlKeyword.ASC.getSqlSegment().toLowerCase().equals(sort.getOrder()), sort.getName()));
        IPage<T> page = this.page(page1, queryWrapper);
        return new Page<>(simplePage.getPage(), simplePage.getRows(), (int) page.getTotal(), page.getRecords());
    }

}
