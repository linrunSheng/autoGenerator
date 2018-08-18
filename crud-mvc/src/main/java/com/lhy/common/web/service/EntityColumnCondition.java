package com.lhy.common.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@FunctionalInterface
public interface EntityColumnCondition<T> {

    /**
     * 实体列处理条件
     * @param wrapper
     * @param columnName
     * @param columnValue
     * @return
     */
    QueryWrapper<T> operate(QueryWrapper<T> wrapper, String columnName, Object columnValue);
}